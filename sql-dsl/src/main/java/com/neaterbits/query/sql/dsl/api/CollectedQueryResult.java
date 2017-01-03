package com.neaterbits.query.sql.dsl.api;

abstract class CollectedQueryResult extends CollectedItem {
	private final Class<?> type;
	
	abstract EQueryResultDimension getDimension();

	abstract EQueryResultGathering getGathering();
	
	// For multi-result
	abstract ECollectionType getCollectionType();
	
	
	CollectedQueryResult(Class<?> type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		this.type = type;
	}

	final Class<?> getType() {
		return type;
	}
}
