
package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Mapped_Multi extends CollectedQueryResult_Mapped {

	private final ECollectionType collectionType;
	
	CollectedQueryResult_Mapped_Multi(Class<?> type, ECollectionType collectionType) {
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
