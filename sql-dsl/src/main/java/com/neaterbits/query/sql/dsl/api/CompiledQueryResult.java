package com.neaterbits.query.sql.dsl.api;

abstract class CompiledQueryResult {

	private final CollectedQueryResult original;

	abstract <T, R> R visit(CompiledQueryResultVisitor<T, R> visitor, T param);
	
	CompiledQueryResult(CollectedQueryResult original) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		this.original = original;
	}

	/*
	final QueryResult getOriginal() {
		return original;
	}
	*/
	
	final ECollectionType getCollectionType() {
		return original.getCollectionType();
	}
	
	final EQueryResultGathering getGathering() {
		return original.getGathering();
	}
	
	final EQueryResultDimension getDimension() {
		return original.getDimension();
	}

	final Class<?> getResultType() {
		return original.getType();
	}
}
