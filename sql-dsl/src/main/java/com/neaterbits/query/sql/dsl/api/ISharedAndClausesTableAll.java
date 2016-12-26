package com.neaterbits.query.sql.dsl.api;


public interface ISharedAndClausesTableAll<MODEL, RESULT, AND_CLAUSES extends ISharedAndClausesTableAll<MODEL, RESULT, AND_CLAUSES>>

		extends ISharedAndClausesTableBase<
				MODEL,
				RESULT,
				AND_CLAUSES,
				
				ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedClauseComparableCommonAll<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedClauseComparableStringAll<MODEL, RESULT, AND_CLAUSES>> {

}
