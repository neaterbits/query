package com.neaterbits.query.sql.dsl.api;
import static com.neaterbits.query.sql.dsl.api.IClassicSelect.alias;
import static com.neaterbits.query.sql.dsl.api.IClassicSelect.intParam;
import static com.neaterbits.query.sql.dsl.api.IClassicSelect.oneFrom;
import static com.neaterbits.query.sql.dsl.api.IClassicSelect.listFrom;
import static com.neaterbits.query.sql.dsl.api.IClassicSelect.sum;
import static com.neaterbits.query.sql.dsl.api.IClassicSelect.list;
import static com.neaterbits.query.sql.dsl.api.IClassicSelect.one;
import static com.neaterbits.query.sql.dsl.api.IClassicSelect.oneOrNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.jpatest.model.Employee;
import com.neaterbits.query.jpatest.model.Person;
import com.neaterbits.query.jpatest.model.Role;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;
import com.neaterbits.query.sql.dsl.api.helper.jpa.QueryTestDSJPANative;
import com.neaterbits.query.sql.dsl.api.helper.jpa.QueryTestDSJPQL;
import com.neaterbits.query.sql.dsl.api.testhelper.BaseSQLAPITest;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSBuilder;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSCheck;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSCombined;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSInMemory;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSStore;

public class ClassicAPITest extends BaseSQLAPITest {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("query-jpa-test");

	private static final QueryMetaModel jpaQueryMetaModel = new JPAQueryMetaModel(emf.getMetamodel());

	private static final QueryTestDSStore nativeDS = new QueryTestDSJPANative("query-jpa-test");
	private static final QueryTestDSStore jpql = new QueryTestDSJPQL("query-jpa-test");
	private static final QueryTestDSStore inMemory = new QueryTestDSInMemory(jpaQueryMetaModel);
	
	private static QueryTestDSCheck store(Consumer<QueryTestDSBuilder> b) {
		
		
		return new QueryTestDSCombined()
				
				//.add(nativeDS)
				.add(jpql)
				
				//.add(inMemory)
				
				.store(b);
	}

	
	@Test
    public void testNameBased() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where(Company::getName).startsWith("Ac")
        	.  and(Company::getName).endsWith("cme")

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
    public void testNameBasedFunctions() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where().lower(Company::getName).isEqualTo("acme")
        	
        	.  and()        .upper(Company::getName).endsWith("CME")

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
    public void testInBasedLiteral() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where(Company::getName).in("Acme", "Baz")
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
    public void testInBasedParam() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");
		
		final InParam<String> inParam = IClassicSelect.inParam(String.class);
		

        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where(Company::getName).in(inParam)

        	.compile();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.executeWith(inParam).setTo("Acme", "Baz")
	        			  .get());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.executeWith(inParam).setTo("Acme", "Baz")
        				  .get());
		});
	}
	
	@Test
    public void testNameBasedEquals() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	.from(Company.class)
        	.where(Company::getName).isEqualTo("Acme")

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
    public void testSingleTable1() {
    	
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
	}
	

	@Test
    public void testSingleTable2() {
    	
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-2, "Foo");

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
    public void testNameWithParamForLike() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

		final ValParam<String> endParam = IClassicSelect.stringParam();
		
		
        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where(Company::getName).startsWith("Ac")
        	.  and(Company::getName).endsWith(endParam)

        	.compile();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.executeWith(endParam).setTo("me").get());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.executeWith(endParam).setTo("me").get());
		});
	}


	@Test
    public void testNameWithParamForEquals() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

		final ValParam<String> eqParam = IClassicSelect.stringParam();
		
        final SingleQuery<CompanyResultVO> startsWithAc =
        		one(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where(Company::getName).isEqualTo(eqParam)
        	.compile();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.executeWith(eqParam).setTo("Acme")
	        			   .get());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.executeWith(eqParam).setTo("Acme")
     			   		.get());
		});
	}
	
	@Test
    public void testNameWithParamForEqualsAndLike() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

		final ValParam<String> eqParam = IClassicSelect.stringParam();
		final ValParam<String> endParam = IClassicSelect.stringParam();
		
		
		
        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where(Company::getName).isEqualTo(eqParam)
        	.  and(Company::getName).endsWith(endParam)

        	.compile();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.executeWith(eqParam).setTo("Acme")
	        			   .and(endParam).setTo("me").get());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.executeWith(eqParam).setTo("Acme")
     			   		.and(endParam).setTo("me").get());
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

	@Test
    public void testNameBasedNested() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)
        	
        	.from(Company.class)

        	.where(Company::getName).startsWith("Ac")
        	.andNest(o -> o
        			.or(Company::getName).endsWith("e")
        			.or(Company::getName).endsWith("a"))
        						

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
    public void testAliasBased() {
    	
		final Company company = alias(Company.class);
		
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

		
        final SingleQuery<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

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
	
	@Test
	public void testMultipleEntities() {
		final MultiQuery<Company> startsWithFoo =
				list(Company.class)
				 .from(Company.class)
			     .where(Company::getName).startsWith("foo").compile();

		assertThat(true).isEqualTo(false);
	}

	@Test
    public void testAliasBasedJoin() {

		final Company  company  = alias(Company.class);
		final Employee employee = alias(Employee.class);
		final Person   person   = alias(Person.class);

		final int acmeCompanyId = 1;
		final int fooCompanyId = 2;

		final Company acme = new Company(acmeCompanyId, "Acme");
		final Company foo  = new Company(fooCompanyId,  "Foo");

		final long fooPerson1Id = 123L;
		final long fooPerson2Id = 124L;
		
		final Person fooPerson1 = new Person(fooPerson1Id, "Foo1", "Person1");
		final Person fooPerson2 = new Person(fooPerson2Id, "Foo2", "Person2");

		final int fooEmployeeId1 = 231; 
		final int fooEmployeeId2 = 232; 

		final Employee fooEmp1 = new Employee(fooEmployeeId1, foo, fooPerson1.getId());
		final Employee fooEmp2 = new Employee(fooEmployeeId2, foo, fooPerson2.getId());
		
        final MultiQuery<CompanyPersonResultVO> startsWithFoo =
        		list(CompanyPersonResultVO.class)

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
	       			// new CompanyPersonResultVO(fooCompanyId, fooPerson1Id, "Foo1", "Person1"),
	       			//new CompanyPersonResultVO(fooCompanyId, fooPerson2Id, "Foo2", "Person2"),
	       			new CompanyPersonResultVO(fooCompanyId, fooPerson2Id, "Foo2", "Person2")
   			);
		});
	}

	@Test
    public void testNameBasedGrouping() {

		final Company acme = new Company(1, "Acme", new BigDecimal("184.2"));
		final Company bar = new Company(2, "Bar", new BigDecimal("134.1"));
		final Company foo = new Company(3, "Foo", new BigDecimal("184.2"));

        final MultiQuery<CompanyResultVO> startsWithAc =
        		list(CompanyResultVO.class)

        	.map(Company::getStockPrice).to(CompanyResultVO::setStockPrice)
        	
        	.from(Company.class)
        	
        	.groupBy(Company::getStockPrice)

        	.having(Company::getStockPrice).isEqualTo(new BigDecimal("184.2"))
        		
        	/*
        	   .sum(Company::getStockPrice).isEqualTo(new BigDecimal("1.2"))
        		.or(Company::getId)		   .isEqualTo(2L)
        	    .or(Company::getName)      .startsWith("Acme")
        	    .orNest(o -> o.and(Company::getName).contains("foo"))
        	    */
        	    
        	    
        	.orderBy(Company::getStockPrice).desc()

        	.compile();
		
		store(s  -> s.add(acme).add(bar).add(foo)).
		check(ds -> {
			checkSelectListOrdered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		
	        		// ascending order
	        		new CompanyResultVO(new BigDecimal("184.2"))
	        		//, new CompanyResultVO(new BigDecimal("134.1")) // due to "having"
    		);
		});

		// Search for foo as well, should return no matches
		/*
		
		store(s  -> s.add(foo)).
		check(ds -> {
			checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute());
		});
		*/
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
    		
	    	final QueryDataSource ds = new QueryDataSourceJPQL(em);
	    	
	    	final ValParam<Integer> param1 = intParam();
	    	final ValParam<Integer> param2 = intParam();
	    	
	    	
	        final SingleQuery<ResultVO > query =
	        		oneOrNull(ResultVO.class)
	
	        	.map(company::getId)		.to(ResultVO::setCompanyId)
	        	.map(person::getId)		   	.to(ResultVO::setPersonId)
	        	.map(person::getFirstName)	.to(ResultVO::setFirstName)
	
	        	.from(company, person, role)
	        	.innerJoin(company, employee)
	        		.on(company::getEmployees)

	        	.where(company::getName)		.startsWith("Foo")
	
	        	.compile();
	
	        	query.prepare(ds)
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

	@Test
    public void testMultiEntity() {
    	
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

		
        final MultiQuery<Company> startsWithAc =
        		list(Company.class)
        		.from(Company.class)
        		
        	.where(Company::getName).startsWith("Ac")

        	.compile();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		new Company(acme.getId(), acme.getName()));
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute());
		});
	}

    
    @Test
    public void testOrderByNullIsFirstOrLast() {
    	assertThat(true).isEqualTo(false);
    }

    @Test
    public void testVariousCombinationsOfGroupByAndOrderByWithNullValues() {
    	assertThat(true).isEqualTo(false);
    }
}	
