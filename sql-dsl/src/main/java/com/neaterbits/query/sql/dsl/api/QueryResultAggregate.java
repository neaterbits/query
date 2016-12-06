package com.neaterbits.query.sql.dsl.api;

abstract class QueryResultAggregate extends QueryResult {

	QueryResultAggregate(Class<?> type) {
		super(type);
	}

	@Override
	final QueryResultMode getMode() {
		return QueryResultMode.SINGLE;
	}
}
