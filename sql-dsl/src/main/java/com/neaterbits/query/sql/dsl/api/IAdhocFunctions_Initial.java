package com.neaterbits.query.sql.dsl.api;

public interface IAdhocFunctions_Initial<
		MODEL,
		RESULT,
		ENTITY,
		RET extends ISharedLogical_Base<MODEL, RESULT>,

		COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Value<MODEL, RESULT, ?, RET>,
		STRING_CLAUSE extends ISharedCondition_Comparable_String_Value<MODEL, RESULT, RET>>

		extends 
				IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, COMPARABLE_CLAUSE>,
				IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> {

}
