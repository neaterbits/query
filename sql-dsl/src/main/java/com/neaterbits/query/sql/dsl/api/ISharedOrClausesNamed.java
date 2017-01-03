package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesNamed<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesNamed<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedOrClausesNamedBase<
				MODEL,
				RESULT,
				OR_CLAUSES,
				
				ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, OR_CLAUSES>,
				ISharedClauseComparableCommonAll<MODEL, RESULT, Long, OR_CLAUSES>,
				ISharedClauseComparableStringAll<MODEL, RESULT, OR_CLAUSES>> {

}
