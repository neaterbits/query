package com.neaterbits.query.sql.dsl.api;

final class CompiledOrderBy extends CompiledResultFields {
	
	private final ESortOrder [] sortOrders;

	CompiledOrderBy(int[] indicesStartingAtOne, FunctionGetter[] optionalGetters, ESortOrder[] sortOrders) {
		super(indicesStartingAtOne, optionalGetters);

		if (indicesStartingAtOne == null) {
			throw new IllegalArgumentException("indicesStartingAtOne == null");
		}

		if (sortOrders == null) {
			throw new IllegalArgumentException("sortOrders == null");
		}

		if (indicesStartingAtOne.length != sortOrders.length) {
			throw new IllegalArgumentException("length mismatch");
		}
	
		this.sortOrders = sortOrders;
	}


	ESortOrder[] getSortOrders() {
		return sortOrders;
	}
}
