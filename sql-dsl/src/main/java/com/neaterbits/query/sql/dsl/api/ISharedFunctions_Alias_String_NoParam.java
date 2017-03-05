package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_String_NoParam<

		MODEL,
		RESULT,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		// comment out since may be used for map as well, not only in conditions
		STRING_CLAUSE // extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>
		> {

	ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> lower();
	ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> upper();
	ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> trim();
	
}
