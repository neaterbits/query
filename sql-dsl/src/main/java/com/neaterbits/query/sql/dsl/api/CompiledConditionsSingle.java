package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;

// Just one single condition
final class CompiledConditionsSingle extends CompiledConditions {

	public CompiledConditionsSingle(CompiledCondition condition) {
		super(Arrays.asList(condition));
	}
}
