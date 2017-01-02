package com.neaterbits.query.sql.dsl.api;

abstract class QueryResultEntity extends QueryResult {

	private final SelectSource selectSource;
	
	QueryResultEntity(SelectSource selectSource) {
		super(selectSource.getType());
		
		this.selectSource = selectSource;
	}

	@Override
	final EQueryResultGathering getGathering() {
		return EQueryResultGathering.ENTITY;
	}

	final SelectSource getSelectSource() {
		return selectSource;
	}
}
