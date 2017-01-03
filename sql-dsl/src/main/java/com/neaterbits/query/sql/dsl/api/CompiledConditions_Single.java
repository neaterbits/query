package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;

// Just one single condition
final class CompiledConditions_Single extends CompiledConditions {

	public CompiledConditions_Single(CompiledCondition condition) {
		super(Arrays.asList(condition));
	}
}
