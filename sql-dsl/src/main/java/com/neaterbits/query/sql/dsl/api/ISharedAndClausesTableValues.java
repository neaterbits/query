package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndClausesTableValues<MODEL, RESULT, AND_CLAUSES extends ISharedAndClausesTableValues<MODEL, RESULT, AND_CLAUSES>>

extends ISharedAndClausesTableBase<
		MODEL,
		RESULT,
		AND_CLAUSES,
		
		ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedClauseComparableCommonValue<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedClauseComparableStringValue<MODEL, RESULT, AND_CLAUSES>> {

}
