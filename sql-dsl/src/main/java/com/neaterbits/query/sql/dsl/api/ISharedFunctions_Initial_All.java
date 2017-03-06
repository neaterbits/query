package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Initial_All<
		MODEL,
		RESULT,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		INTEGER_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>,
		DOUBLE_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Double, RET>,
		STRING_RET extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>>

	extends 

		ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, RET, INTEGER_RET, LONG_RET, DOUBLE_RET, STRING_RET>,
		ISharedFunctions_Transform_Initial_Alias<MODEL, RESULT, RET, INTEGER_RET, LONG_RET, DOUBLE_RET, STRING_RET>

{

}
