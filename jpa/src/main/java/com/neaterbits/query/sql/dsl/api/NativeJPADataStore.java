package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;

final class NativeJPADataStore extends ManagedJPADataStore {

	NativeJPADataStore(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected QueryDataSource createDataSource(EntityManager entities) {
		return new QueryDataSourceJPANative(entities);
	}
}
