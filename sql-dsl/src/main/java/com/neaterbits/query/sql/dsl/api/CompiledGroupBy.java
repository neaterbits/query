package com.neaterbits.query.sql.dsl.api;

final class CompiledGroupBy extends CompiledResultFields {

	CompiledGroupBy(int[] indicesStartingAtOne, FunctionGetter[] optionalGetters) {
		super(indicesStartingAtOne, optionalGetters);
	}
}
