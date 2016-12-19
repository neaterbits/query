package com.neaterbits.query.sql.dsl.api;


public interface ConditionClauseAlias<MODEL, RESULT, R, L extends LogicalClauses<MODEL, RESULT>>
	extends ConditionClause<MODEL, RESULT, R, L>{

	/*
	L isEqualTo(Supplier<R> other);

	L isNotEqualTo(Supplier<R> other);
	*/
	
	
}
