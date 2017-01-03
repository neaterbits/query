package com.neaterbits.query.sql.dsl.api;

abstract class CollectedQueryResult_Mapped extends CollectedQueryResult {
	CollectedQueryResult_Mapped(Class<?> type) {
		super(type);
	}

	@Override
	final EQueryResultGathering getGathering() {
		return EQueryResultGathering.MAPPED;
	}
}
