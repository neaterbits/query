package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_Initial <
		MODEL,
		RESULT,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>,
		STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>>

		extends ISharedFunctions_Base<MODEL, RESULT, RET>,
			ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE>,
			ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> {

}