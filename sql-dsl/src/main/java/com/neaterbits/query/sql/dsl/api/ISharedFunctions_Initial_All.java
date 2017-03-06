package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Initial_All<
		MODEL,
		RESULT,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>,
		STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>>

	extends 

		ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE>,
		ISharedFunctions_Transform_Initial_Alias<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE>

{

}
