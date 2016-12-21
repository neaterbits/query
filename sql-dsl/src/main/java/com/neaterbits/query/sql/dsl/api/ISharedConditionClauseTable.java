package com.neaterbits.query.sql.dsl.api;

public interface ISharedConditionClauseTable<MODEL, RESULT, R, L extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedConditionClause<MODEL, RESULT, R, L> {

	/*
	<T> L isEqualTo(Function<T, R> other);

	<T> L isNotEqualTo(Function<T, R> other);
	*/
	
}
