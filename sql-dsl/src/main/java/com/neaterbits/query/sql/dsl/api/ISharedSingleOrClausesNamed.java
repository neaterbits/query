package com.neaterbits.query.sql.dsl.api;

public interface ISharedSingleOrClausesNamed<
		MODEL, 
		RESULT,
		OR_CLAUSES extends ISharedSingleOrClausesNamed<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedLogicalClauses<MODEL, RESULT> {

	    ISharedClauseConditionAll<MODEL, RESULT, Integer, OR_CLAUSES> or(IFunctionInteger<RESULT> getter);
	    
	    ISharedClauseComparableStringAll<MODEL, RESULT, OR_CLAUSES> or(StringFunction<RESULT> getter);

}
