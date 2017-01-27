package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctionsNamedInitial<
		MODEL,
		RESULT,
		RET extends ISharedLogicalClauses<MODEL, RESULT>,

		COMPARABLE_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, ?, RET>,
		STRING_CLAUSE extends ISharedClauseComparableStringBase<MODEL, RESULT, RET>>
	
		extends ISharedFunctionsBase<MODEL, RESULT, RET, COMPARABLE_CLAUSE, STRING_CLAUSE>,
				ISharedFunctionsNamedArithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE>,
				ISharedFunctionsNamedString<MODEL, RESULT, RET, STRING_CLAUSE> {
    
}
