package com.neaterbits.query.sql.dsl.api;

final class CompiledGroupBy extends CompiledResultFields {

	CompiledGroupBy(int[] indicesStartingAtOne, CompiledFieldReference[] getters) {
		super(indicesStartingAtOne, getters);
	}
}
