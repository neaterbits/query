package com.neaterbits.query.sql.dsl.api;

abstract class CompiledOrderBy extends CompiledResultFields {
	
	private final ESortOrder [] sortOrders;

	CompiledOrderBy(int[] indicesStartingAtOne, CompiledFieldReference [] optionalGetters, ESortOrder[] sortOrders) {
		super(indicesStartingAtOne, optionalGetters);

		if (sortOrders == null) {
			throw new IllegalArgumentException("sortOrders == null");
		}
	
		this.sortOrders = sortOrders;
	}


	final ESortOrder[] getSortOrders() {
		return sortOrders;
	}
}
