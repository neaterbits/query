package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_String_Undecided<
		MODEL,
		RESULT,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_LENGTH_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		ALIAS_LENGTH_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
		>
		
		extends
		
			ISharedFunctions_String_Named_All<MODEL, RESULT, NAMED_RET, NAMED_LENGTH_RET, NAMED_STRING_RET>,
			ISharedFunctions_String_Alias_All<MODEL, RESULT, ALIAS_RET, ALIAS_LENGTH_RET, ALIAS_STRING_RET>,
		
			ISharedFunctions_String_NoParam_Base<
					MODEL,
					RESULT,
					ISharedMapFunctions_String_StringResult_Undecided<
						MODEL, RESULT,
						NAMED_RET, ALIAS_RET,
						NAMED_LENGTH_RET, NAMED_LENGTH_RET,
						ALIAS_LENGTH_RET, ALIAS_LENGTH_RET>,
		
					// eg lower().xyz, return only the functions that return String (so skip length())
					ISharedMapFunctions_String_StringResult_Undecided<
						MODEL, RESULT,
						NAMED_RET, ALIAS_RET,
						NAMED_STRING_RET, NAMED_STRING_RET,
						ALIAS_STRING_RET, ALIAS_STRING_RET>>

{

}
