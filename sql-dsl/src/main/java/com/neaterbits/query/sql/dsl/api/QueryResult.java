package com.neaterbits.query.sql.dsl.api;

abstract class QueryResult {
	private final Class<?> type;
	
	abstract QueryResultMode getMode();

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
