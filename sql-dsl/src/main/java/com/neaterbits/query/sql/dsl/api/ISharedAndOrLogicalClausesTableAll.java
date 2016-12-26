package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndOrLogicalClausesTableAll<
										MODEL,
										RESULT,
										AND_CLAUSES extends ISharedAndClausesTableAll<MODEL, RESULT, AND_CLAUSES>,
										OR_CLAUSES extends ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES>> 
										
		extends
			ISharedAndClausesTableAll<MODEL, RESULT, AND_CLAUSES>,
			ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES>,
			ISharedLogicalClauses<MODEL, RESULT> {

}
