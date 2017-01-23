package com.neaterbits.query.sql.dsl.api;


public interface ISharedAndClausesNamedAll<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedAndClauses<MODEL, RESULT>,
			NESTED_OR_CLAUSES extends ISharedOrClausesNamed<MODEL, RESULT>>

		extends ISharedAndClausesNamedBase<
				MODEL,
				RESULT,
				AND_CLAUSES,
				NESTED_OR_CLAUSES,
				
				ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedClauseComparableCommonAll<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedClauseComparableStringAll<MODEL, RESULT, AND_CLAUSES>> {

}