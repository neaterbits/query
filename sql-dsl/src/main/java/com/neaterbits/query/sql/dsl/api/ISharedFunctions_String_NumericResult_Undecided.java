package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_NumericResult_Undecided<
		MODEL,
		RESULT,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
	
		NAMED_LENGTH_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		ALIAS_LENGTH_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
	>
	
	extends 
		ISharedFunctions_String_NumericResult_Named<MODEL, RESULT, NAMED_RET, NAMED_LENGTH_RET>,
		ISharedFunctions_String_NumericResult_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_LENGTH_RET>
	{
	
}
