package com.neaterbits.query.sql.dsl.api;

public interface ISharedMap_Functions_String_StringResult_Alias<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		STRING_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		NO_PARAM_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, RET>>
	
	extends ISharedFunctions_String_StringResult_Alias<MODEL, RESULT, RET, STRING_RET>,
			    ISharedFunctions_String_StringResult_NoParam_Base<MODEL, RESULT,
			    
			    
			    ISharedMap_Functions_String_StringResult_Alias<
			    	MODEL, RESULT,
			    	RET,
			    	STRING_RET,
			    	NO_PARAM_STRING_RET
			    
			    >> {

}
