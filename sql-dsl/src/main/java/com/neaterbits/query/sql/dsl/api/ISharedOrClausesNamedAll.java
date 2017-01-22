package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesNamedAll<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesNamedAll<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>, NESTED_AND_CLAUSES extends ISharedAndClauses<MODEL, RESULT>>

		extends ISharedOrClausesNamedBase<
				MODEL,
				RESULT,
				OR_CLAUSES,
				NESTED_AND_CLAUSES,
				
				ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, OR_CLAUSES>,
				ISharedClauseComparableCommonAll<MODEL, RESULT, Long, OR_CLAUSES>,
				ISharedClauseComparableStringAll<MODEL, RESULT, OR_CLAUSES>> {

}
