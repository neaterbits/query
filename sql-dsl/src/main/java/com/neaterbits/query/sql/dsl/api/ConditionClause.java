package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ConditionClause<MODEL, RESULT, R, L extends LogicalClauses<MODEL, RESULT>> {

	L isEqualTo(R other);
	
	<T> L isEqualTo(Function<T, R> other);
	
	L isNotEqualTo(R other);
	
	<T> L isNotEqualTo(Function<T, R> other);
	
	L in(@SuppressWarnings("unchecked") R ... values);
}
