package com.neaterbits.query.sql.dsl.api;


class QueryTestDSJPANative extends QueryTestDSJPAManaged {

	QueryTestDSJPANative(String persistenceUnitName) {
		super(new NativeJPADataStore(persistenceUnitName));
	}
}
