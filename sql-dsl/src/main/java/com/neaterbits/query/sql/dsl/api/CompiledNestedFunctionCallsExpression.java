package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledNestedFunctionCallsExpression extends Expression {

	private final NestedFunctionCallsExpression original;
	private final CompiledFieldReference field;
	
	CompiledNestedFunctionCallsExpression(NestedFunctionCallsExpression original, CompiledFieldReference field) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (field == null) {
			throw new IllegalArgumentException("field == null");
		}

		this.original = original;
		this.field = field;
	}

	List<FunctionExpression> getFunctions() {
		return original.getFunctions().getFunctions();
	}
	
	CompiledFieldReference getField() {
		return field;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onCompiledNestedFunctionCalls(this, param);
	}
}
