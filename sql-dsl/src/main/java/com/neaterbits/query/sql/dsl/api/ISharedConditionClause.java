package com.neaterbits.query.sql.dsl.api;

public interface ISharedConditionClause<MODEL, RESULT, R, L extends ISharedLogicalClauses<MODEL, RESULT>> {

	L isEqualTo(R other);

	L isEqualTo(Param<R> other);
	
	L isNotEqualTo(R other);

	L isNotEqualTo(Param<R> other);
	
	L in(@SuppressWarnings("unchecked") R ... values);
}
