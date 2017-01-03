package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Entity_Multi extends CollectedQueryResult_Entity {

	private final ECollectionType collectionType;
	
	CollectedQueryResult_Entity_Multi(SharedSelectSource selectSource, ECollectionType collectionType) {
		super(selectSource);

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
