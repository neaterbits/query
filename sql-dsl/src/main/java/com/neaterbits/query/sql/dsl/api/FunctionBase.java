package com.neaterbits.query.sql.dsl.api;

abstract class FunctionBase extends Expression {
	
	abstract <T, R> R visit(FunctionVisitor<T, R> visitor, T param);

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onFunction(this, param);
	}
}
