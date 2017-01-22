package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndOrLogicalClausesNamedAll<
										MODEL,
										RESULT,
										AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
										OR_CLAUSES extends ISharedOrClausesNamedAll<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>> 
										
		extends
			ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
			ISharedOrClausesNamedAll<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>,
			ISharedLogicalClauses<MODEL, RESULT> {

}
