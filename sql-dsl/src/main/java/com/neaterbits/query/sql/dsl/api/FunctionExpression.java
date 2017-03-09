package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class FunctionExpression extends Expression {

	private final FunctionExpressionBase<Expression> function;
	
	FunctionExpression(FunctionBase function, List<Expression> parameters) {
		this.function = new FunctionExpressionBase<>(function, parameters);
	}
		
	
	FunctionExpression(FunctionBase function, Expression ... parameters) {
		this.function = new FunctionExpressionBase<>(function, parameters);
	}
	
	FunctionBase getFunction() {
		return function.getFunction();
	}


	// Necessary? Can pass in NestedFuncionCallExpression instead since only should be at most one field
	/*
	@Deprecated
	FieldExpression getField() {
		return parameters;
	}
	*/
	
	List<Expression> getParameters() {
		return function.getParameters();
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onFunction(this, param);
	}
}
