package com.neaterbits.query.sql.dsl.api;

public interface ConditionClauseTable<MODEL, RESULT, R, L extends LogicalClauses<MODEL, RESULT>>
	extends ConditionClause<MODEL, RESULT, R, L> {

	/*
	<T> L isEqualTo(Function<T, R> other);

	<T> L isNotEqualTo(Function<T, R> other);
	*/
	
}
