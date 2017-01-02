package com.neaterbits.query.sql.dsl.api;

/** 
 * Base datasource for generating queries
 * 
 */

abstract class QueryDataSourceGenBase extends QueryDataSourceDB {

	abstract PreparedQueryBuilder createBuilder();
	
	abstract <QUERY> DSPreparedQuery<QueryDataSourceDB> prepare(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query);

	@Override
	<QUERY> DSPreparedQuery<QueryDataSourceDB> prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(q, query);
	}

	@Override
	<QUERY> DSPreparedQuery<QueryDataSourceDB> prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(q, query);
	}

	private <QUERY> DSPreparedQuery<QueryDataSourceDB> prepareQuery(ExecutableQuery<QUERY> q, QUERY query) {

		final PreparedQueryBuilder sb = createBuilder();

		return prepare(sb, q, query);
	}
}
