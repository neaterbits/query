package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndOrLogicalClausesTableValues<
										MODEL,
										RESULT,
										AND_CLAUSES extends ISharedAndClausesTableValues<MODEL, RESULT, AND_CLAUSES>,
										OR_CLAUSES extends ISharedOrClausesTableValues<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedAndClausesTableValues<MODEL, RESULT, AND_CLAUSES>, ISharedOrClausesTableValues<MODEL, RESULT, OR_CLAUSES>,
		ISharedLogicalClauses<MODEL, RESULT> {

}
