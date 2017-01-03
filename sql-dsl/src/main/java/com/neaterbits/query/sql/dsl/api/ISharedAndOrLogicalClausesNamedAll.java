package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndOrLogicalClausesNamedAll<
										MODEL,
										RESULT,
										AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES>,
										OR_CLAUSES extends ISharedOrClausesNamed<MODEL, RESULT, OR_CLAUSES>> 
										
		extends
			ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES>,
			ISharedOrClausesNamed<MODEL, RESULT, OR_CLAUSES>,
			ISharedLogicalClauses<MODEL, RESULT> {

}
