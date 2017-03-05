package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_Initial_And_NoParam<
		MODEL,
		RESULT,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		// commented out since reused for mapping functions 
		INTEGER_CLAUSE, // extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE, // extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>,
		STRING_CLAUSE //  extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>
		
		>
		
		extends 
			ISharedFunctions_Alias_Initial<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE>,
			ISharedFunctions_Alias_Arithmetic_NoParam<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE>,
			ISharedFunctions_Alias_String_NoParam<MODEL, RESULT, RET, STRING_CLAUSE> 

{

}
