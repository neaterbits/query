package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndOrLogicalClausesNamedValues<
										MODEL,
										RESULT,
										AND_CLAUSES extends ISharedAndClausesNamedValues<MODEL, RESULT, AND_CLAUSES>,
										OR_CLAUSES extends ISharedOrClausesNamedValues<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedAndClausesNamedValues<MODEL, RESULT, AND_CLAUSES>, ISharedOrClausesNamedValues<MODEL, RESULT, OR_CLAUSES>,
		ISharedLogicalClauses<MODEL, RESULT> {

}
