package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class FunctionExpressionBase<T> {
	private final FunctionBase function;
	private final List<T> parameters;
	
	FunctionExpressionBase(FunctionBase function, List<T> parameters) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters == null");
		}

		this.function = function;
		this.parameters = parameters;
	}
		
	
	FunctionExpressionBase(FunctionBase function, T ... parameters) {
		this(function, parameters.length == 0 ? Collections.emptyList() : Arrays.asList(parameters));
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
	
	List<T> getParameters() {
		return parameters;
	}
}
