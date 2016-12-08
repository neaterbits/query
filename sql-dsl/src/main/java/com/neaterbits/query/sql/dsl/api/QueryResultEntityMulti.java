package com.neaterbits.query.sql.dsl.api;

final class QueryResultEntityMulti extends QueryResultEntity {

	QueryResultEntityMulti(Class<?> type) {
		super(type);
	}

	@Override
	QueryResultDimension getDimension() {
		return QueryResultDimension.MULTI;
	}
	
}
