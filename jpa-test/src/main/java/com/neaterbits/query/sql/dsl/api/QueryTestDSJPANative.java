package com.neaterbits.query.sql.dsl.api;


class QueryTestDSJPANative extends QueryTestDSJPAManaged {

	QueryTestDSJPANative(String persistenceUnitName) {
		super(new JPADataConfigNative(persistenceUnitName), new NativeJPADataStore(persistenceUnitName));
	}
}
