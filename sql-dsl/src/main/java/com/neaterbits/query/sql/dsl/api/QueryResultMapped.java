package com.neaterbits.query.sql.dsl.api;

abstract class QueryResultMapped extends QueryResult {
	QueryResultMapped(Class<?> type) {
		super(type);
	}

	@Override
	final QueryResultGathering getGathering() {
		return QueryResultGathering.MAPPED;
	}
}
