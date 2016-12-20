package com.neaterbits.query.sql.dsl.api.helper;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.QueryDataSourceJPA;

public final class QueryTestDSJPA extends QueryTestDS {

	private final EntityManagerFactory emf;
	private final Function<Object, Object> getPK;

	public QueryTestDSJPA(String persistenceUnitName) {

		this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);

		this.getPK = instance -> emf.getPersistenceUnitUtil().getIdentifier(instance); 
	}

	@Override
	public QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {

		final QueryTestDSBuilder b = new QueryTestDSBuilder(getPK);

		final EntityManager em = emf.createEntityManager();

		final List<TestInstance> instances = b.getInstances();
		
		boolean ok = false;

		em.getTransaction().begin();
		
		try {
			
			dsBuilder.accept(b);
			
			
			dumpMetaModel(em.getMetamodel());
		
			for (TestInstance instance : instances) {
				em.persist(instance.getInstance());
			}

			em.getTransaction().commit();
			ok = true;
		}
		finally {
			
			try {
				if (!ok && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			}
			finally {
				em.close();
			}
		}

		return new Checker(emf, instances);
	}

	private static void dumpMetaModel(Metamodel model) {
		for (EntityType<?> entityType : model.getEntities()) {
			System.out.println("Got entity: " + entityType.getName() + " of type " + entityType.getJavaType().getName() + ", persistence type " + entityType.getPersistenceType());
		}
	}

	private static class Checker implements QueryTestDSCheck {
		private final EntityManagerFactory emf;
		private final List<TestInstance> instances;

		public Checker(EntityManagerFactory emf, List<TestInstance> instances) {
			this.emf = emf;
			this.instances = instances;
		}

		@Override
		public void check(Consumer<QueryDataSource> testBuilder) {

			final EntityManager em = emf.createEntityManager();
			final QueryDataSource dataSource = new QueryDataSourceJPA(em);

			try {
				testBuilder.accept(dataSource);
			}
			catch (RuntimeException ex) {
				System.err.println("Got runtime exception in check: " + ex);
				ex.printStackTrace(System.err);
			}
			finally {
				safelyDeleteInstances(em);
			}
		}
		
		private void safelyDeleteInstances(EntityManager em) {
			try {
				em.getTransaction().begin();

				for (TestInstance instance : instances) {
					
					final Object found = em.find(instance.getInstance().getClass(), instance.getPK());
					
					if (found == null) {
						throw new IllegalStateException("found == null");
					}
					
					em.remove(found);
				}
				
				em.getTransaction().commit();
			}
			finally {
				em.close();
			}
		}
	}
}
