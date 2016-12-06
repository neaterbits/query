package com.neaterbits.query.sql.dsl.api;

final class QueryResultMappedSingle extends QueryResultMapped {

	QueryResultMappedSingle(Class<?> type) {
		super(type);
	}

	@Override
	QueryResultMode getMode() {
		return QueryResultMode.SINGLE;
	}
}