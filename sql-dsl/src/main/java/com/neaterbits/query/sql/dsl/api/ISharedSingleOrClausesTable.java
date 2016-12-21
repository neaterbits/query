package com.neaterbits.query.sql.dsl.api;

public interface ISharedSingleOrClausesTable<
		MODEL, 
		RESULT,
		OR_CLAUSES extends ISharedSingleOrClausesTable<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedLogicalClauses<MODEL, RESULT> {

	    ISharedClauseConditionAll<MODEL, RESULT, Integer, OR_CLAUSES> or(IFunctionInteger<RESULT> getter);
	    
	    ISharedClauseComparativeStringAll<MODEL, RESULT, OR_CLAUSES> or(StringFunction<RESULT> getter);

}
