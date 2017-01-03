package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Mapped_Single extends CollectedQueryResult_Mapped {

	CollectedQueryResult_Mapped_Single(Class<?> type) {
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
