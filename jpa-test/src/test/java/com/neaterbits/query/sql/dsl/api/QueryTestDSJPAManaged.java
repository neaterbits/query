package com.neaterbits.query.sql.dsl.api;

abstract class QueryTestDSJPAManaged extends QueryTestDSJPABase {
	
	protected QueryTestDSJPAManaged(JPADataConfig dataConfig, ManagedJPADataStore dataStore) {
		super(dataConfig, dataStore);
	}
}
