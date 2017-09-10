package com.neaterbits.query.sql.dsl.api;

public interface ISharedMap_Functions_String_StringResult_Undecided<
	MODEL,
	RESULT,
	
	NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
	ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
	
	
	NAMED_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	NAMED_NO_PARAM_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

	ALIAS_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
	ALIAS_NO_PARAM_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>>
	
	extends ISharedFunctions_String_StringResult_Undecided<MODEL, RESULT, NAMED_RET, ALIAS_RET, NAMED_STRING_RET, ALIAS_STRING_RET>,
		    ISharedFunctions_String_StringResult_NoParam_Base<MODEL, RESULT,
		    
		    
		    ISharedMap_Functions_String_StringResult_Undecided<
		    	MODEL, RESULT,
		    	NAMED_RET,
		    	ALIAS_RET,
		    	
		    	NAMED_STRING_RET,
		    	NAMED_NO_PARAM_STRING_RET,
		    
		    	ALIAS_STRING_RET,
		    	ALIAS_NO_PARAM_STRING_RET
		    >> {

}
