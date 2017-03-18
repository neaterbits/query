package com.neaterbits.query.sql.dsl.api;

public final class QueryTestDSJPA extends QueryTestDSJPABase {

	public QueryTestDSJPA(JPADataConfig config) {
		super(config, (JPADataStore)config.getDataStore());
	}
}
