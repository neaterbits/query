package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_Arithmetic_NoParam<
		MODEL,
		RESULT,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		// comment out since may be used for map as well, not only in conditions
		INTEGER_CLAUSE, // extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE // extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>
		
		> {

	ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs();
	
	ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt();
	
}
