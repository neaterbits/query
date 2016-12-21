package com.neaterbits.query.sql.dsl.api;

abstract class QueryResultEntity extends QueryResult {

	QueryResultEntity(Class<?> type) {
		super(type);
	}

	@Override
	final EQueryResultGathering getGathering() {
		return EQueryResultGathering.ENTITY;
	}
}
