package com.neaterbits.query.sql.dsl.api;

abstract class CompiledExpression {

	abstract <T, R> R visit(CompiledExpressionVisitor<T, R> visitor, T param);
	
}
