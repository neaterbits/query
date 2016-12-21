package com.neaterbits.query.sql.dsl.api;

public interface ISharedSingleOrClausesTable<
		MODEL, 
		RESULT,
		OR_CLAUSES extends ISharedSingleOrClausesTable<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedLogicalClauses<MODEL, RESULT> {

	    ISharedConditionClause<MODEL, RESULT, Integer, OR_CLAUSES> or(IntegerFunction<RESULT> getter);
	    
	    ISharedStringClause<MODEL, RESULT, OR_CLAUSES> or(StringFunction<RESULT> getter);

}
