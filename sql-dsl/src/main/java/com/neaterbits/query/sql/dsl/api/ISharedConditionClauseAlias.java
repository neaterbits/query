package com.neaterbits.query.sql.dsl.api;


public interface ISharedConditionClauseAlias<MODEL, RESULT, R, L extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedConditionClause<MODEL, RESULT, R, L>{

	/*
	L isEqualTo(Supplier<R> other);

	L isNotEqualTo(Supplier<R> other);
	*/
	
	
}
