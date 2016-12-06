package com.neaterbits.query.sql.dsl.api;

final class QueryResultEntitySingle extends QueryResultEntity {

	QueryResultEntitySingle(Class<?> type) {
		super(type);
	}

	@Override
	QueryResultMode getMode() {
		return QueryResultMode.SINGLE;
	}
}
