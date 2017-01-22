package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndClausesNamedValues<
				MODEL,
				RESULT,
				AND_CLAUSES extends ISharedAndClausesNamedValues<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
				NESTED_OR_CLAUSES extends ISharedOrClausesNamed<MODEL, RESULT>>

	extends ISharedAndClausesNamedBase<
		MODEL,
		RESULT,
		AND_CLAUSES,
		NESTED_OR_CLAUSES,
		
		ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedClauseComparableCommonValue<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedClauseComparableStringValue<MODEL, RESULT, AND_CLAUSES>> {

}
