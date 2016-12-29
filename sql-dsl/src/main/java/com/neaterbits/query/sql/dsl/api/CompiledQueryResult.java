package com.neaterbits.query.sql.dsl.api;

abstract class CompiledQueryResult {

	private final QueryResult original;

	abstract <T, R> R visit(CompiledQueryResultVisitor<T, R> visitor, T param);
	
	CompiledQueryResult(QueryResult original) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		this.original = original;
	}

	final QueryResult getOriginal() {
		return original;
	}
	
	final ECollectionType getCollectionType() {
		return original.getCollectionType();
	}
}
