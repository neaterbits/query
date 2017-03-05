package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_String_NoParam<
		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		> {

    ISharedFunctions_Named_String<MODEL, RESULT, RET, STRING_CLAUSE> lower();
    ISharedFunctions_Named_String<MODEL, RESULT, RET, STRING_CLAUSE> upper();
    ISharedFunctions_Named_String<MODEL, RESULT, RET, STRING_CLAUSE> trim();
	
}
