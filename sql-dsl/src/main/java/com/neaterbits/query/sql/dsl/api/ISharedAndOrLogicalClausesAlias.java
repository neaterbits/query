package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndOrLogicalClausesAlias<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedAndClausesAlias<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
			OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>> 

		extends
			ISharedAndClausesAlias<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
			ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>,
			ISharedLogicalClauses<MODEL, RESULT> {

}
