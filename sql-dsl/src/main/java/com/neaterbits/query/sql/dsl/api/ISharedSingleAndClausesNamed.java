package com.neaterbits.query.sql.dsl.api;

public interface ISharedSingleAndClausesNamed<
		MODEL, 
		RESULT,
		AND_CLAUSES extends ISharedSingleAndClausesNamed<MODEL, RESULT, AND_CLAUSES>>


	extends ISharedLogicalClauses<MODEL, RESULT> {

    ISharedClauseConditionNamed<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<RESULT> getter);

    ISharedClauseComparableStringAll<MODEL, RESULT, AND_CLAUSES> and(StringFunction<RESULT> getter);
	
}
