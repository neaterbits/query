package com.neaterbits.query.sql.dsl.api;

final class CompiledConditionNested extends CompiledCondition {

	private final CompiledConditions sub;

	CompiledConditionNested(CompiledConditions sub) {
		
		if (sub == null) {
			throw new IllegalArgumentException("sub == null");
		}

		this.sub = sub;
	}

	CompiledConditions getSub() {
		return sub;
	}
}
