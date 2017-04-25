package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_StringResult_Initial<
		MODEL,
		RESULT,
		
		NAMED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		
		NAMED_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		ALIAS_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
		>

	extends
	
		ISharedFunctions_String_StringResult_Named<MODEL, RESULT, NAMED_RET, NAMED_STRING_RET>,
		ISharedFunctions_String_StringResult_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_STRING_RET>,

		ISharedFunctions_String_StringResult_NoParam_Base<
				MODEL,
				RESULT,
	
				// eg lower().xyz, return only the functions that return String (so skip length())
				ISharedFunctions_StringResult_Initial<
					MODEL, RESULT,
					NAMED_RET, ALIAS_RET,
					NAMED_STRING_RET, ALIAS_STRING_RET>> {
	
}
