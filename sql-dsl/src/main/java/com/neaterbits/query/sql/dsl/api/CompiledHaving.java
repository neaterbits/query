package com.neaterbits.query.sql.dsl.api;

final class CompiledHaving {

	private final CompiledConditions conditions;

	CompiledHaving(CompiledConditions conditions) {
		
		if (conditions == null) {
			throw new IllegalArgumentException("conditions == null");
		}

		this.conditions = conditions;
	}

	CompiledConditions getConditions() {
		return conditions;
	}
}
