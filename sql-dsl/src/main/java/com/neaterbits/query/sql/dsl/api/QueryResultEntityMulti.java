package com.neaterbits.query.sql.dsl.api;

final class QueryResultEntityMulti extends QueryResultEntity {

	QueryResultEntityMulti(Class<?> type) {
		super(type);
	}

	@Override
	EQueryResultDimension getDimension() {
		return EQueryResultDimension.MULTI;
	}
	
}
