package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Entity_Single extends CollectedQueryResult_Entity {

	CollectedQueryResult_Entity_Single(SharedSelectSource selectSource) {
		super(selectSource);
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
