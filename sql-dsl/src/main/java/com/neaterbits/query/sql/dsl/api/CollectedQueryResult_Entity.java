package com.neaterbits.query.sql.dsl.api;

abstract class CollectedQueryResult_Entity extends CollectedQueryResult {

	private final SharedSelectSource selectSource;
	
	CollectedQueryResult_Entity(SharedSelectSource selectSource) {
		super(selectSource.getType());
		
		this.selectSource = selectSource;
	}

	@Override
	final EQueryResultGathering getGathering() {
		return EQueryResultGathering.ENTITY;
	}

	final SharedSelectSource getSelectSource() {
		return selectSource;
	}
}
