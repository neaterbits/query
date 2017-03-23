package com.neaterbits.query.sql.dsl.api;

public class CompiledOrderByMapped extends CompiledOrderBy {

	CompiledOrderByMapped(int[] indicesStartingAtOne, CompiledFieldReference[] optionalGetters, ESortOrder[] sortOrders) {
		super(indicesStartingAtOne, optionalGetters, sortOrders);
		if (indicesStartingAtOne == null) {
			throw new IllegalArgumentException("indicesStartingAtOne == null");
		}
		
		if (indicesStartingAtOne.length == 0) {
			throw new IllegalArgumentException("no indices");
		}
	}
}
