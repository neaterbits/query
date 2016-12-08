package com.neaterbits.query.sql.dsl.api;

abstract class QueryResultEntity extends QueryResult {

	QueryResultEntity(Class<?> type) {
		super(type);
	}

	@Override
	final QueryResultGathering getGathering() {
		return QueryResultGathering.ENTITY;
	}
}
