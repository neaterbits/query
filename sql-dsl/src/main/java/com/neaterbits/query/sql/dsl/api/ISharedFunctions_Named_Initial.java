package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_Initial<
		MODEL,
		RESULT,
		RET, // can be used for mapping as well extends ISharedLogical_Base<MODEL, RESULT>,

		// comment out since may be used for map as well, not only in conditions
		INTEGER_CLAUSE, // extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE, // extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>,
		STRING_CLAUSE //extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>
		
		>
	
		extends ISharedFunctions_Inital_Base<MODEL, RESULT>,
				ISharedFunctions_Named_All<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> {
    
}
