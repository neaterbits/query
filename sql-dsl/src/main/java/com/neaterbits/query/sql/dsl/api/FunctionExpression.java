package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class FunctionExpression extends Expression {

	private final FunctionExpressionBase<Expression> function;
	
	FunctionExpression(FunctionBase function, List<Expression> parameters) {
		if (!parameters.isEmpty()) {
			final Expression fieldParam = parameters.get(0);
			
			if (!(fieldParam instanceof FieldExpression  || fieldParam instanceof ExpressionList)) {
				throw new IllegalStateException("Expected param to be field or sub: " + fieldParam.getClass().getSimpleName());
			}
		}
		
		this.function = new FunctionExpressionBase<>(function, parameters);
	}
		
	
	FunctionExpression(FunctionBase function, Expression ... parameters) {
		
		if (parameters.length != 0) {
			final Expression fieldParam = parameters[0];
			
			if (!(fieldParam instanceof FieldExpression || fieldParam instanceof ExpressionList)) {
				throw new IllegalStateException("Expected param to be field or sub: " + fieldParam.getClass().getSimpleName());
			}
		}
		
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
