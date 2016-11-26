package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;

// Just one single condition
final class CompiledConditionsSingle extends CompiledConditions {

	public CompiledConditionsSingle(ConditionImpl condition) {
		super(Arrays.asList(condition));
	}
}
