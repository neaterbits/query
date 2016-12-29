package com.neaterbits.query.sql.dsl.api;

final class QueryResultEntitySingle extends QueryResultEntity {

	QueryResultEntitySingle(Class<?> type) {
		super(type);
	}

	@Override
	EQueryResultDimension getDimension() {
		return EQueryResultDimension.SINGLE;
	}

	@Override
	ECollectionType getCollectionType() {
		throw new UnsupportedOperationException("Not a collection type");
	}
}
