package com.neaterbits.query.sql.dsl.api;

public interface ISharedSingleAndClausesTable<
		MODEL, 
		RESULT,
		AND_CLAUSES extends ISharedSingleAndClausesTable<MODEL, RESULT, AND_CLAUSES>>


	extends ISharedLogicalClauses<MODEL, RESULT> {

    ISharedClauseConditionTable<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<RESULT> getter);

    ISharedClauseComparativeStringAll<MODEL, RESULT, AND_CLAUSES> and(StringFunction<RESULT> getter);
	
}
