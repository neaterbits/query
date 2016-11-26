package com.neaterbits.query.sql.dsl.api;

final class CompiledCondition {
	private final ConditionImpl original;
	private final CompiledGetter lhs;
	
	CompiledCondition(ConditionImpl original, CompiledGetter lhs) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (lhs == null) {
			throw new IllegalArgumentException("lhs == null");
		}
		
		this.original = original;
		this.lhs = lhs;
	}

	ConditionImpl getOriginal() {
		return original;
	}

	CompiledGetter getLhs() {
		return lhs;
	}
}
