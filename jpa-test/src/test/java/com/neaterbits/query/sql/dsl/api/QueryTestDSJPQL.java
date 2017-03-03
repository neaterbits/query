package com.neaterbits.query.sql.dsl.api;

final class QueryTestDSJPQL extends QueryTestDSJPAManaged {

	QueryTestDSJPQL(String persistenceUnitName) {
		super(new JPQLDataStore(persistenceUnitName));
	}
}

