package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndOrLogicalClausesTable<
										MODEL,
										RESULT,
										AND_CLAUSES extends ISharedAndClausesTable<MODEL, RESULT, AND_CLAUSES>,
										OR_CLAUSES extends ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES>> 
										
		extends
			ISharedAndClausesTable<MODEL, RESULT, AND_CLAUSES>,
			ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES>,
			ISharedLogicalClauses<MODEL, RESULT> {

}
