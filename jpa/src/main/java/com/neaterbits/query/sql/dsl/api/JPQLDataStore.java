package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;

final class JPQLDataStore extends ManagedJPADataStore {

	JPQLDataStore(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected QueryDataSource createDataSource(EntityManager em) {
		return new QueryDataSourceJPQL(em);
	}
}
