package com.neaterbits.query.sql.dsl.api;

public interface ConditionClause<MODEL, RESULT, R, L extends LogicalClauses<MODEL, RESULT>> {

	L isEqualTo(R other);

	L isEqualTo(Param<R> other);
	
	L isNotEqualTo(R other);

	L isNotEqualTo(Param<R> other);
	
	L in(@SuppressWarnings("unchecked") R ... values);
}
