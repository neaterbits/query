package com.neaterbits.query.sql.dsl.api;

abstract class QueryTestDSJPAManaged extends QueryTestDSJPABase {
	
	protected QueryTestDSJPAManaged(ManagedJPADataStore dataStore) {
		super(dataStore);
	}
}
