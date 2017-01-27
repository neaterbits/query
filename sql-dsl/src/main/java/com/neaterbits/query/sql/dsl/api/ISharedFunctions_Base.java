package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Base<
		MODEL,
		RESULT,
		RET extends ISharedLogical_Base<MODEL, RESULT>,

		COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, RET>,
		STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>> {
	
}