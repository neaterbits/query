package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

final class PreparedQueryConditionComparison extends PreparedQueryCondition {

	private final FieldReference lhs;
	private final PreparedQueryComparisonRHS rhs;
	
	PreparedQueryConditionComparison(FieldReference lhs, PreparedQueryComparisonRHS rhs) {
		
		if (lhs == null) {
			throw new IllegalArgumentException("lhs == null");
		}
		
		if (rhs == null) {
			throw new IllegalArgumentException("rhs == null");
		}
		
		this.lhs = lhs;
		this.rhs = rhs;
	}

	FieldReference getLhs() {
		return lhs;
	}

	PreparedQueryComparisonRHS getRhs() {
		return rhs;
	}

	@Override
	boolean isUnresolved() {
		return rhs.isUnresolved();
	}

	@Override
	void walk(Consumer<PreparedQueryConditionComparison> consumer) {
		
		consumer.accept(this);
		
	}
}
