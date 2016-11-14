package com.neaterbits.query.sql.dsl.api;

/**
 * Representation of query where we have compiled all information 
 *
 */

final class QueryCompiledImpl {

	private final Class<?> resultType;

	QueryCompiledImpl(Class<?> resultType) {

		if (resultType == null) {
			throw new IllegalArgumentException("resultType == null");
		}

		this.resultType = resultType;
	}
	
	static QueryCompiledImpl compile(QueryCollectorImpl collector) {
		if (collector == null) {
			throw new IllegalArgumentException("collector == null");
		}
		
		// Check all clauses etc
		
		throw new UnsupportedOperationException("TODO");
	}
}
