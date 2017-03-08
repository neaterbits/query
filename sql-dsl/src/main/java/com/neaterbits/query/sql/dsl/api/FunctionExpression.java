package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class FunctionExpression extends Expression {

	private final FunctionBase function;
	private final List<Expression> parameters;
	
	FunctionExpression(FunctionBase function, List<Expression> parameters) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters == null");
		}

		this.function = function;
		this.parameters = parameters;
	}
		
	
	FunctionExpression(FunctionBase function, Expression ... parameters) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		this.function = function;
		this.parameters = parameters.length == 0 ? Collections.emptyList() : Arrays.asList(parameters);
	}
	
	FunctionBase getFunction() {
		return function;
	}


	// Necessary? Can pass in NestedFuncionCallExpression instead since only should be at most one field
	/*
	@Deprecated
	FieldExpression getField() {
		return parameters;
	}
	*/
	
	List<Expression> getParameters() {
		return parameters;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onFunction(this, param);
	}
}
