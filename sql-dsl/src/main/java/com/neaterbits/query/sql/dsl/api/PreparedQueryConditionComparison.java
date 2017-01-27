package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Consumer;

final class PreparedQueryConditionComparison extends PreparedQueryCondition {

	private final List<FunctionBase> functions;
	private final FieldReference lhs;
	private final PreparedQueryComparisonRHS rhs;
	
	PreparedQueryConditionComparison(List<FunctionBase> functions, FieldReference lhs, PreparedQueryComparisonRHS rhs) {
		
		
		if (lhs == null) {
			throw new IllegalArgumentException("lhs == null");
		}
		
		if (rhs == null) {
			throw new IllegalArgumentException("rhs == null");
		}

		this.functions = functions;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	

	List<FunctionBase> getLhsFunctions() {
		return functions;
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
