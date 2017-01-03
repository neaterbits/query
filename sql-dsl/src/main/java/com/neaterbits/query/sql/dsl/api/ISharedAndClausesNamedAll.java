package com.neaterbits.query.sql.dsl.api;


public interface ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES>>

		extends ISharedAndClausesNamedBase<
				MODEL,
				RESULT,
				AND_CLAUSES,
				
				ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedClauseComparableCommonAll<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedClauseComparableStringAll<MODEL, RESULT, AND_CLAUSES>> {

}
