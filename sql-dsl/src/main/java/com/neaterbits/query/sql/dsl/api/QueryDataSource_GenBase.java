package com.neaterbits.query.sql.dsl.api;

/** 
 * Base datasource for generating queries
 * 
 */

abstract class QueryDataSource_GenBase extends QueryDataSource_DB {

	abstract PreparedQueryBuilder createBuilder();
	
	abstract <QUERY> DSPreparedQuery<QueryDataSource_DB> prepare(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query);

	@Override
	<QUERY> DSPreparedQuery<QueryDataSource_DB> prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(q, query);
	}

	@Override
	<QUERY> DSPreparedQuery<QueryDataSource_DB> prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(q, query);
	}

	private <QUERY> DSPreparedQuery<QueryDataSource_DB> prepareQuery(ExecutableQuery<QUERY> q, QUERY query) {

		final PreparedQueryBuilder sb = createBuilder();

		return prepare(sb, q, query);
	}
}
