package com.neaterbits.query.sql.dsl.api;

public interface ISharedSingleAndClausesTable<
		MODEL, 
		RESULT,
		AND_CLAUSES extends ISharedSingleAndClausesTable<MODEL, RESULT, AND_CLAUSES>>


	extends ISharedLogicalClauses<MODEL, RESULT> {

    ISharedConditionClauseTable<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<RESULT> getter);

    ISharedStringClause<MODEL, RESULT, AND_CLAUSES> and(StringFunction<RESULT> getter);
	
}
