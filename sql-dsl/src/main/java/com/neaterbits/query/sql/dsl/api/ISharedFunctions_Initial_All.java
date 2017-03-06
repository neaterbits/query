package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Initial_All<
		MODEL,
		RESULT,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>,
		STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>>

	extends 

		ISharedFunctions_Named_Transform_Initial<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE>,
		ISharedFunctions_Alias_Transform_Initial<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE>

{

}
