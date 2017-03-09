package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledNestedFunctionCallsExpression extends CompiledExpression {

	//private final NestedFunctionCallsExpression original;
	private final List<CompiledFunctionExpression> functions;
	//private final CompiledFieldReference field;
	
	/*
	@Deprecated
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
	*/
	
	CompiledNestedFunctionCallsExpression(List<CompiledFunctionExpression> functions) {
		
		if (functions == null) {
			throw new IllegalArgumentException("functions == null");
		}
		
		if (functions.size() < 2) {
			throw new IllegalArgumentException("less than 2 functions");
		}
		
		this.functions = functions;
	}

	List<CompiledFunctionExpression> getFunctions() {
		return functions; // original.getFunctions().getFunctions();
	}
	
	/*
	@Deprecated
	CompiledFieldReference getField() {
		return field;
	}
	*/

	@Override
	<T, R> R visit(CompiledExpressionVisitor<T, R> visitor, T param) {
		return visitor.onNestedFunctionCalls(this, param);
	}
}
