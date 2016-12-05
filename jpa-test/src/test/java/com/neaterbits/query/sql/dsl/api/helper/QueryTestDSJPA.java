package com.neaterbits.query.sql.dsl.api.helper;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.QueryDataSourceJPA;

public final class QueryTestDSJPA extends QueryTestDS implements QueryTestDSCheck {

	private final EntityManagerFactory emf;

	public QueryTestDSJPA(String persistenceUnitName) {
		this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	}
	
	@Override
	public QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {

		final QueryTestDSBuilder b = new QueryTestDSBuilder();
		
		dsBuilder.accept(b);
		
		final List<Object> instances = b.getInstances();
		
		boolean ok = false;
		
		final EntityManager em = emf.createEntityManager();
		
		dumpMetaModel(em.getMetamodel());
		
		em.getTransaction().begin();
		
		try {
			for (Object instance : instances) {
				em.persist(instance);
			}
			
			em.getTransaction().commit();
			ok = true;
		}
		finally {
			
			try {
				if (!ok) {
					em.getTransaction().rollback();
				}
			}
			finally {
				em.close();
			}
		}
		
		
		return this;
	}

	private static void dumpMetaModel(Metamodel model) {
		for (EntityType<?> entityType : model.getEntities()) {
			System.out.println("Got entity: " + entityType.getName() + " of type " + entityType.getJavaType().getName() + ", persistence type " + entityType.getPersistenceType());
		}
	}
	
	@Override
	public void check(Consumer<QueryDataSource> testBuilder) {

		final EntityManager em = emf.createEntityManager();
		final QueryDataSource dataSource = new QueryDataSourceJPA(em);

		try {
			testBuilder.accept(dataSource);
		}
		finally {
			em.close();
		}
	}
}
