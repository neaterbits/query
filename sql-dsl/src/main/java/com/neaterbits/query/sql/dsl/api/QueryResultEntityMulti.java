package com.neaterbits.query.sql.dsl.api;

final class QueryResultEntityMulti extends QueryResultEntity {

	private final ECollectionType collectionType;
	
	QueryResultEntityMulti(Class<?> type, ECollectionType collectionType) {
		super(type);

		if (collectionType == null) {
			throw new IllegalArgumentException("collectionType == null");
		}

		this.collectionType = collectionType;
	}

	@Override
	EQueryResultDimension getDimension() {
		return EQueryResultDimension.MULTI;
	}

	@Override
	ECollectionType getCollectionType() {
		return collectionType;
	}
}
