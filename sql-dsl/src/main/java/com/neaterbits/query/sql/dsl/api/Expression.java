package com.neaterbits.query.sql.dsl.api;

abstract class Expression {

	abstract <T, R> R visit(ExpressionVisitor<T, R> visitor,  T param);
	
}
