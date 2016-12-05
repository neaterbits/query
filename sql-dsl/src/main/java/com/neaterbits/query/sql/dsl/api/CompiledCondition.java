package com.neaterbits.query.sql.dsl.api;

final class CompiledCondition {
	private final ConditionImpl original;
	private final CompiledFieldReference lhs;
	private final ConditionValueImpl value;
	
	CompiledCondition(ConditionImpl original, CompiledFieldReference lhs, ConditionValueImpl value) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (lhs == null) {
			throw new IllegalArgumentException("lhs == null");
		}
		
		this.original = original;
		this.lhs = lhs;
		this.value = value;
	}

	ConditionImpl getOriginal() {
		return original;
	}

	CompiledFieldReference getLhs() {
		return lhs;
	}

	ConditionValueImpl getValue() {
		return value;
	}
}
