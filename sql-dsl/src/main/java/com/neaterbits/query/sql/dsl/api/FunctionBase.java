package com.neaterbits.query.sql.dsl.api;

abstract class FunctionBase {
	
	abstract <T, R> R visit(FunctionVisitor<T, R> visitor, T param);

}
