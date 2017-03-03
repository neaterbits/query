package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

abstract class JPADataStore 
		extends TransactionalDataStore<EntityManagerFactory, EntityManager, EntityTransaction> {

	public JPADataStore(String persistenceUnitName) {
		super(Persistence.createEntityManagerFactory(persistenceUnitName));
	}
	
	@Override
	protected final EntityManager intOpenEntities(EntityManagerFactory ctx) {
		return ctx.createEntityManager();
	}

	@Override
	protected Object getPrimaryKey(Object instance) {
		return getCtx().getPersistenceUnitUtil().getIdentifier(instance);
	}

	@Override
	protected final EntityTransaction beginTransaction(EntityManager em) {
		final EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		return transaction;
	}

	@Override
	protected final void commitTransaction(EntityTransaction transaction) {
		transaction.commit();
	}

	@Override
	protected final boolean isTransactionActive(EntityTransaction transaction) {
		return transaction.isActive();
	}

	@Override
	protected final void rollbackTransaction(EntityTransaction transaction) {
		transaction.rollback();
	}

	@Override
	protected final void closeEntities(EntityManager em) {
		em.close();
	}

	private static void dumpMetaModel(Metamodel model) {
		for (EntityType<?> entityType : model.getEntities()) {
			System.out.println("Got entity: " + entityType.getName() + " of type " + entityType.getJavaType().getName() + ", persistence type " + entityType.getPersistenceType());
		}
	}
}
