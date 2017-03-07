package com.neaterbits.query.sql.dsl.api;

final class FunctionExpression extends Expression {

	private final FunctionBase function;
	@Deprecated
	private final FieldExpression field;
	
	public FunctionExpression(FunctionBase function, FieldExpression field) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		this.function = function;
		this.field = field;
	}
	
	FunctionBase getFunction() {
		return function;
	}


	// Necessary? Can pass in NestedFuncionCallExpression instead since only should be at most one field
	@Deprecated
	FieldExpression getField() {
		return field;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onFunction(this, param);
	}
	
}
