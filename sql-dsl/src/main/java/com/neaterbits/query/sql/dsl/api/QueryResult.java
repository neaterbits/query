package com.neaterbits.query.sql.dsl.api;

abstract class QueryResult extends QueryBuilderItem {
	private final Class<?> type;
	
	abstract QueryResultDimension getDimension();

	abstract QueryResultGathering getGathering();

	QueryResult(Class<?> type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		this.type = type;
	}

	final Class<?> getType() {
		return type;
	}
}
