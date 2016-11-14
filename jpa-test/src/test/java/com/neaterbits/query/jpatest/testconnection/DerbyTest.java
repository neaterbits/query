package com.neaterbits.query.jpatest.testconnection;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.neaterbits.query.jpatest.model.Company;

import static org.assertj.core.api.Assertions.assertThat;



public class DerbyTest {

	
	// See http://stackoverflow.com/questions/8459284/using-hibernate-with-embedded-derby
	// thought this is eclipselink
	@Test
	public void test() {
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory("query-jpa-test");
		final EntityManager em = emf.createEntityManager();
		
		assertNotNull(em);

		final Company company = new Company();
		
		company.setName("Acme Inc");
		
		em.getTransaction().begin();
		
		em.persist(company);
		em.getTransaction().commit();
		
		final List results = em.createQuery("select c from Company c").getResultList();
		
		assertThat(results.size()).isEqualTo(1);
		
		final Company c = (Company)results.get(0); 
		assertThat(c.getName()).isEqualTo("Acme Inc");
	}
}


