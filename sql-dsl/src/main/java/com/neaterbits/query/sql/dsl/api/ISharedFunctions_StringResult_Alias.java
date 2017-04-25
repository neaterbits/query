package com.neaterbits.query.sql.dsl.api;

// Any functions that return a String value
public interface ISharedFunctions_StringResult_Alias<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
		> 
	extends ISharedFunctions_String_StringResult_Alias<MODEL, RESULT, RET, STRING_CLAUSE> {

}
