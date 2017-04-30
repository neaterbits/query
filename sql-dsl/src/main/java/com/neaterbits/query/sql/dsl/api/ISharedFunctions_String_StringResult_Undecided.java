package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_StringResult_Undecided<
	MODEL,
	RESULT,
	
	NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
	ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
	
	
	NAMED_STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	ALIAS_STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>>
	
	extends ISharedFunctions_String_StringResult_Named<MODEL, RESULT, NAMED_RET, NAMED_STRING_CLAUSE>,
			ISharedFunctions_String_StringResult_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_STRING_CLAUSE> {
		
}
