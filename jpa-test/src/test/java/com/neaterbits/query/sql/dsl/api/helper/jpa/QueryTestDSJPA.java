package com.neaterbits.query.sql.dsl.api.helper.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.QueryDataSourceJPA;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSBasePersistent;

public final class QueryTestDSJPA extends QueryTestDSBasePersistent<EntityManagerFactory, EntityManager, EntityTransaction> {

	public QueryTestDSJPA(String persistenceUnitName) {
		super(Persistence.createEntityManagerFactory(persistenceUnitName), (ctx, instance) -> ctx.getPersistenceUnitUtil().getIdentifier(instance));
	}
	
	@Override
	protected EntityManager openEntities(EntityManagerFactory ctx) {
		return ctx.createEntityManager();
	}

	@Override
	protected EntityTransaction beginTransaction(EntityManager em) {
		final EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		return transaction;
	}

	@Override
	protected void persist(EntityManager em, Object instance) {
		em.persist(instance);
	}

	@Override
	protected void remove(EntityManager em, Object instance, Object pk) {

		final Object found = em.find(instance.getClass(), pk);

		if (found == null) {
			throw new IllegalStateException("found == null");
		}

		em.remove(found);
	}

	@Override
	protected void commitTransaction(EntityTransaction transaction) {
		transaction.commit();
	}

	@Override
	protected boolean isTransactionActive(EntityTransaction transaction) {
		return transaction.isActive();
	}

	@Override
	protected void rollbackTransaction(EntityTransaction transaction) {
		transaction.rollback();
	}

	@Override
	protected void closeEntities(EntityManager em) {
		em.close();
	}

	@Override
	protected QueryDataSource createDataSource(EntityManager em) {
		return new QueryDataSourceJPA(em);
	}

	private static void dumpMetaModel(Metamodel model) {
		for (EntityType<?> entityType : model.getEntities()) {
			System.out.println("Got entity: " + entityType.getName() + " of type " + entityType.getJavaType().getName() + ", persistence type " + entityType.getPersistenceType());
		}
	}
}
