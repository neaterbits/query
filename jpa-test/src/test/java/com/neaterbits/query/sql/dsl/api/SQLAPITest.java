package com.neaterbits.query.sql.dsl.api;


import static com.neaterbits.query.sql.dsl.api.Select.alias;
import static com.neaterbits.query.sql.dsl.api.Select.intParam;
import static com.neaterbits.query.sql.dsl.api.Select.selectOneOrNull;
import static com.neaterbits.query.sql.dsl.api.Select.selectList;
import static com.neaterbits.query.sql.dsl.api.Select.oneFrom;
import static com.neaterbits.query.sql.dsl.api.Select.listFrom;
import static com.neaterbits.query.sql.dsl.api.Select.sum;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.jpatest.model.Employee;
import com.neaterbits.query.jpatest.model.Person;
import com.neaterbits.query.jpatest.model.Role;
import com.neaterbits.query.sql.dsl.api.helper.QueryTestDSBuilder;
import com.neaterbits.query.sql.dsl.api.helper.QueryTestDSCheck;
import com.neaterbits.query.sql.dsl.api.helper.QueryTestDSJPA;

public class SQLAPITest {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("query-jpa-test");

	@Test
    public void testTableBased() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleQuery<CompanyResultVO> startsWithAc =
        		selectOneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where(Company::getName).startsWith("Ac")

        	.compile();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.execute());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.execute());
		});
	}

	@Test
    public void testSingleTable() {
    	
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-2, "Foo");

        final SingleQuery<Company> startsWithAc =
        		oneFrom(Company.class)
        			.where(Company::getName).startsWith("Ac")
        			.compile();
		
		store(s  -> s.add(acme)
					 .add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new Company(-1, acme.getName()),
	        		startsWithAc,
	        		q -> q.execute());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.execute());
		});

        final MultiQuery<Company> startsWithAcOrEndsWithoo =
        		listFrom(Company.class)
        			.where(Company::getName).startsWith("Ac")
        			.   or(Company::getName).endsWith("oo")
        			.compile();
	
		store(s  -> s.add(acme)
				 .add(foo)).
		check(ds -> {
			checkSelectListUnordered(
			    ds,
       			startsWithAcOrEndsWithoo,
       			q -> q.execute(),
       			new Company(-1, acme.getName()),
       			new Company(-2, foo.getName()));
			});
	}
	
	
	@Test
    public void testSum() {
		final Company acme = new Company(-1, "Acme", new BigDecimal("100.90"));
		final Company foo = new Company(-2, "Foo", new BigDecimal("75.49"));
		
		final SingleQuery<BigDecimal> query = 
				sum(Company::getStockPrice).from(Company.class)
				.compile();
		
		store(s  -> s.add(acme)
				 .add(foo))
		.check(ds -> {
			final BigDecimal ret = query.prepare(ds).execute();
			
			assertThat(ret).isNotNull();
			
			System.out.println("Got ret " + ret);
			
			assertThat(ret.compareTo(new BigDecimal("176.39"))).isEqualTo(0);
		});
		
	}
	
	
	private static QueryTestDSCheck store(Consumer<QueryTestDSBuilder> b) {
		return new QueryTestDSJPA("query-jpa-test").store(b);
	}
	
	
	private <T> void checkSelectOneOrNull(QueryDataSource ds, T expected, SingleQuery<T> query, Function<PreparedQueryOps<T>, T> execute) {
    			
		PreparedQueryOps<T> ops = query.prepare(ds);

		final T result = execute.apply(ops);
    			
        assertThat(query).isNotNull();
    	
    	if (expected == null) {
    		assertThat(result).isNull();
    	}
    	else {
    		assertThat(result).isNotSameAs(expected);
    		assertThat(result).isEqualTo(expected);
    	}
	}

	private <T> void checkSelectListUnordered(QueryDataSource ds, MultiQuery<T> query, Function<PreparedQueryOps<List<T>>, List<T>> execute, T ... expected) {

		final List<T> ret = checkSelectListCommon(ds, query, execute, expected);
		
		// Check that contains the same number of items as the expected
		
		final CountMap<T> resultMap = new CountMap<>(ret);
		
		final CountMap<T> expectedMap = new CountMap<>(Arrays.asList(expected));
		
		assertThat(resultMap).isEqualTo(expectedMap);
	}
	
	private <T> List<T> checkSelectListCommon(QueryDataSource ds, MultiQuery<T> query, Function<PreparedQueryOps<List<T>>, List<T>> execute, T ... expected) {
		PreparedQueryOps<List<T>> ops = query.prepare(ds);

		final List<T> result = execute.apply(ops);

		for (int i = 0; i < result.size(); ++ i) {
			System.out.println("result " + i + ": " + result.get(i));
		}

        assertThat(query).isNotNull();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expected.length);
		
        return result;
	}

	@Test
    public void testAliasBased() {
    	
		final Company company = alias(Company.class);
		
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

		
        final SingleQuery<CompanyResultVO> startsWithAc =
        		selectOneOrNull(CompanyResultVO.class)

        	.map(company::getName).to(CompanyResultVO::setName)
        	
        	.from(company)
        	.where(company::getName).startsWith("Ac")

        	.compile();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.execute());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.execute());
		});
	}
	

	static class CompanyPersonResultVO {
		private Long companyId;
		private Long personId;
		private String firstName;
		private String lastName;
		
		CompanyPersonResultVO() {
			
		}
		
		CompanyPersonResultVO(long companyId, long personId, String firstName, String lastName) {
			this.companyId = companyId;
			this.personId = personId;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public void setCompanyId(Long companyId) {
			this.companyId = companyId;
		}

		public void setPersonId(Long personId) {
			this.personId = personId;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (companyId ^ (companyId >>> 32));
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + (int) (personId ^ (personId >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompanyPersonResultVO other = (CompanyPersonResultVO) obj;
			if (companyId != other.companyId)
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (personId != other.personId)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "CompanyPersonResultVO [companyId=" + companyId + ", personId=" + personId + ", firstName="
					+ firstName + ", lastName=" + lastName + "]";
		}
	}
	
	@Test
    public void testAliasBasedJoin() {
    	
		final Company  company  = alias(Company.class);
		final Employee employee = alias(Employee.class);
		final Person   person   = alias(Person.class);
		
		final int acmeCompanyId = 1;
		final int fooCompanyId = 2;
		
		final Company acme = new Company(acmeCompanyId, "Acme");
		final Company foo = new Company(fooCompanyId, "Foo");

		final long fooPerson1Id = 123L;
		final long fooPerson2Id = 124L;
		
		final Person fooPerson1 = new Person(fooPerson1Id, "Foo1", "Person1");
		final Person fooPerson2 = new Person(fooPerson2Id, "Foo2", "Person2");
		
		final int fooEmployeeId1 = 231; 
		final int fooEmployeeId2 = 232; 
		
		final Employee fooEmp1 = new Employee(fooEmployeeId1, foo, fooPerson1.getId());
		final Employee fooEmp2 = new Employee(fooEmployeeId2, foo, fooPerson2.getId());
		
        final MultiQuery<CompanyPersonResultVO> startsWithFoo =
        		selectList(CompanyPersonResultVO.class)

        	.map(company::getId)      .to(CompanyPersonResultVO::setCompanyId)
        	.map(person::getId)       .to(CompanyPersonResultVO::setPersonId)
        	.map(person::getFirstName).to(CompanyPersonResultVO::setFirstName)
        	.map(person::getLastName) .to(CompanyPersonResultVO::setLastName)
        	
        	.from(company, employee, person)

        	.innerJoin(company, employee)
        		.on(company::getEmployees)

        	.innerJoin(employee, person)
        		.compare(employee::getPersonId, person::getId)

        	.where(company::getName).startsWith("Fo")

        	.compile();
		
		store(s  -> s.add(acme).add(foo)).
		check(ds -> {
			/*
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.execute());
			 */
		});

		// Search for foo as well, should return no matches
		store(s  -> s
				.add(acme).add(foo)
				.add(fooEmp1).add(fooPerson1)
				.add(fooEmp2).add(fooPerson2)).
		check(ds -> {
			checkSelectListUnordered(
				    ds,
	       			startsWithFoo,
	       			q -> q.execute(),
	       			new CompanyPersonResultVO(fooCompanyId, fooPerson1Id, "Foo1", "Person1"),
	       			new CompanyPersonResultVO(fooCompanyId, fooPerson1Id, "Foo1", "Person1"),
	       			new CompanyPersonResultVO(fooCompanyId, fooPerson2Id, "Foo2", "Person2"),
	       			new CompanyPersonResultVO(fooCompanyId, fooPerson2Id, "Foo2", "Person2"));
		});
	}
	
	
    //@Test
    public void testAliasBasedObsolete() {
    	EntityManager em = null;
    	try {
    		em = emf.createEntityManager();

    		final Company 	company  = alias(Company.class);
			final Person 	person   = alias(Person.class); 
			final Employee 	employee = alias(Employee.class);
			final Role 		role	 = alias(Role.class);
    		
	    	final QueryDataSource ds = new QueryDataSourceJPA(em);
	    	
	    	final Param<Integer> param1 = intParam();
	    	final Param<Integer> param2 = intParam();
	    	
	    	
	        final SingleQuery<ResultVO > query =
	        		selectOneOrNull(ResultVO.class)
	
	        	.map(company::getId)		.to(ResultVO::setCompanyId)
	        	.map(person::getId)		   	.to(ResultVO::setPersonId)
	        	.map(person::getFirstName)	.to(ResultVO::setFirstName)
	
	        	.from(company, person, role)
	        	.innerJoin(company, employee)
	        		.on(company::getEmployees)

	        	.where(company::getName)		.startsWith("Foo")
	
	        	.compile();
	
	        	ResultVO result = query.prepare(ds)
	        	 .executeWith(param1).setTo(123)
	        	         .and(param2).setTo(345)
	        	  .get();
	
	        	
	        	//result = query.execute(b -> b
				//	.with(param1).setTo(123)
				//	 .and(param2).setTo(456)
					
				//	.on(ds)
				//);
	        	
	        assertThat(query).isNotNull();
    	}
    	finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    }

    private static class Foo {
    	private String test;

		public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
		}
    }
}
