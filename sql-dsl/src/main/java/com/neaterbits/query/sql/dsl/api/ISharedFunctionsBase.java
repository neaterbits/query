package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctionsBase<
		MODEL,
		RESULT,
		RET extends ISharedLogicalClauses<MODEL, RESULT>,

		COMPARABLE_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, ?, RET>,
		STRING_CLAUSE extends ISharedClauseComparableStringBase<MODEL, RESULT, RET>> {
	
}