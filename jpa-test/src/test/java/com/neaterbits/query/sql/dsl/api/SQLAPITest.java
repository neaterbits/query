package com.neaterbits.query.sql.dsl.api;


import static com.neaterbits.query.sql.dsl.api.Select.alias;
import static com.neaterbits.query.sql.dsl.api.Select.intParam;
import static com.neaterbits.query.sql.dsl.api.Select.selectOne;
import static org.assertj.core.api.Assertions.assertThat;

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

public class SQLAPITest {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("query-jpa-test");

	@Test
    public void testTableBased() {
    	
		dumpMetaModel(emf.getMetamodel());
		
    	EntityManager em = null;
    	try {
    		em = emf.createEntityManager();

	    	final QueryDataSource ds = new QueryDataSourceJPA(em);
	    	
	    	final Param<Integer> param1 = intParam();
	    	final Param<Integer> param2 = intParam();
	    	
	        final SingleQuery<ResultVO > query =
	        		selectOne(ResultVO.class)
	
	        	.map(Company::getId)		.to(ResultVO::setCompanyId)
	        	.map(Person::getId)			.to(ResultVO::setPersonId)
	        	.map(Person::getFirstName)	.to(ResultVO::setFirstName)
	
	        	.from(Company.class, Person.class, Employee.class, Role.class)
	
	        	.where(Company::getName)		.startsWith("Foo")
	        	  .and(Company::getId)			.isEqualTo(Employee::getCompanyId)
	
	        	// where-clause
	        	//.whereString(Company::getId)
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
	
	private static void dumpMetaModel(Metamodel model) {
		for (EntityType<?> entityType : model.getEntities()) {
			System.out.println("Got entity: " + entityType.getName() + " of type " + entityType.getJavaType().getName() + ", persistence type " + entityType.getPersistenceType());
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
	        		selectOne(ResultVO.class)
	
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
