package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;

public final class JPADataConfigJPQL extends JPADataConfig {
	public JPADataConfigJPQL(String persistenceUnitName) {
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
		return new JPQLDataStore(getPersistenceUnitName());
	}
}
