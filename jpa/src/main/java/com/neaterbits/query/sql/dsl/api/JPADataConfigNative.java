package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;

// TODO: have two different configs sublasses? or one class and two modes? (native and JPQL?)
public final class JPADataConfigNative extends JPADataConfig {

	public JPADataConfigNative(String persistenceUnitName) {
		super(persistenceUnitName);
	}


	@Override
	protected QueryDataSource getDataSource() {
		
		// TODO: Close EMF? must do reference counting
		
		final JPADataStore dataStore = (JPADataStore)getDataStore();
		
		final EntityManager entityManager = dataStore.openEntities();
		
		return getDataStore().createDataSource(entityManager);
		
	}

	@Override
	protected DataStore<EntityManager> getDataStore() {
		return new NativeJPADataStore(getPersistenceUnitName());
	}
}
