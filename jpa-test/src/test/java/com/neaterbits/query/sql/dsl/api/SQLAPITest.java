package com.neaterbits.query.sql.dsl.api;


import static com.neaterbits.query.sql.dsl.api.Select.alias;
import static com.neaterbits.query.sql.dsl.api.Select.intParam;
import static com.neaterbits.query.sql.dsl.api.Select.selectOneOrNull;
import static com.neaterbits.query.sql.dsl.api.Select.selectOneFrom;
import static com.neaterbits.query.sql.dsl.api.Select.oneFrom;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

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
    public void testTableBasedOne() {
    	
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

    @Test
    public void testAliasBased() {
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
	
	        	.where(company::getName)		.startsWith("Foo")
	        	  .and(company::getId)			.isEqualTo(employee::getCompanyId)
	
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
