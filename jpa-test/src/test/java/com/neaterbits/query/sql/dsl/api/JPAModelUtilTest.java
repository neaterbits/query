package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.jpatest.model.Employee;

public class JPAModelUtilTest {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("query-jpa-test");

	@Test
	public void testFooFindAssociation() {

		
		JPAEntityModel.findEntityField(Company.class, Employee.class, emf.getMetamodel());
		
		//JPAEntityModel.findEntityField(Employee.class, Company.class, emf.getMetamodel());
		
	}
	
}
