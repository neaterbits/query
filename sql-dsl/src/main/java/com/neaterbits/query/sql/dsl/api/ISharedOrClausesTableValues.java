package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesTableValues<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesTableValues<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedOrClausesTableBase<
			MODEL,
			RESULT,
			OR_CLAUSES,
			
			ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedClauseComparableCommonValue<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedClauseComparableStringValue<MODEL, RESULT, OR_CLAUSES>> {

}
