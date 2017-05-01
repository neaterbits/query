package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Consumer;

final class PreparedQueryConditionComparison extends PreparedQueryCondition {

	/*
	private final List<FunctionCalcBase> functions;
	private final CompiledFieldReference compiledLhs;
	private final FieldReference lhs;
	*/
	private final ExecutableQueryExpressions lhs;
	private final PreparedQueryComparisonRHS rhs;
	
	PreparedQueryConditionComparison(ExecutableQueryExpressions lhs,/* List<FunctionCalcBase> functions, CompiledFieldReference compiledLhs, FieldReference lhs, */ PreparedQueryComparisonRHS rhs) {
		/*
		if (compiledLhs == null) {
			throw new IllegalArgumentException("compiledLhs == null");
		}

		if (lhs == null) {
			throw new IllegalArgumentException("lhs == null");
		}
		
		if (rhs == null) {
			throw new IllegalArgumentException("rhs == null");
		}

		this.functions = functions;
		this.compiledLhs = compiledLhs;
		this.lhs = lhs;
		*/
		this.lhs = lhs;
		this.rhs = rhs;
	}

	/*
	List<FunctionCalcBase> getLhsFunctions() {
		return functions;
	}

	CompiledFieldReference getCompiledLhs() {
		return compiledLhs;
	}

	FieldReference getLhs() {
		return lhs;
	}
	*/
	
	ExecutableQueryExpressions getLhs() {
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
