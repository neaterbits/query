package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesNamedValues<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesNamedValues<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedOrClausesNamedBase<
			MODEL,
			RESULT,
			OR_CLAUSES,
			
			ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedClauseComparableCommonValue<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedClauseComparableStringValue<MODEL, RESULT, OR_CLAUSES>> {

}
