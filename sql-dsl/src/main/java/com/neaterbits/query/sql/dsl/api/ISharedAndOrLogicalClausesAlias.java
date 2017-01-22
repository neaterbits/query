package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndOrLogicalClausesAlias<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedAndClausesAliasBase<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
			OR_CLAUSES extends ISharedOrClausesAliasBase<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>> 

		extends
			ISharedAndClausesAliasBase<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
			ISharedOrClausesAliasBase<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>,
			ISharedLogicalClauses<MODEL, RESULT> {

}
