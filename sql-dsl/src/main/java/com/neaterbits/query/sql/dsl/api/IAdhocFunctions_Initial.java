package com.neaterbits.query.sql.dsl.api;

public interface IAdhocFunctions_Initial<
		MODEL,
		RESULT,
		ENTITY,
		RET extends ISharedLogical_Base<MODEL, RESULT>,

		INTEGER_CLAUSE extends ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE extends ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, RET>,
		STRING_CLAUSE extends ISharedComparison_Comparable_String_Value<MODEL, RESULT, RET>>

		extends 
				IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE>,
				IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> {

}
