package com.neaterbits.query.sql.dsl.api;

/** 
 * Base datasource for generating queries
 * 
 */

abstract class QueryDataSource_GenBase extends QueryDataSource_DB {

	abstract PreparedQueryBuilder createBuilder();
	
	abstract <QUERY> PreparedQuery_DS<QueryDataSource_DB> prepare(PreparedQueryBuilder sb, QueryDialect_SQL dialect, ExecutableQuery<QUERY> q, QUERY query);

	@Override
	<QUERY> PreparedQuery_DS<QueryDataSource_DB> prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(q, query);
	}

	@Override
	<QUERY> PreparedQuery_DS<QueryDataSource_DB> prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(q, query);
	}

	protected abstract QueryDialect_SQL getDialect();
	
	private <QUERY> PreparedQuery_DS<QueryDataSource_DB> prepareQuery(ExecutableQuery<QUERY> q, QUERY query) {

		final PreparedQueryBuilder sb = createBuilder();

		return prepare(sb, getDialect(), q, query);
	}
}
