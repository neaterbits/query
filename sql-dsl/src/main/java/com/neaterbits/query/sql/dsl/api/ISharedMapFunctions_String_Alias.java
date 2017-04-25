package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_String_Alias<
	MODEL,
	RESULT,
	RET extends ISharedFunction_After<MODEL, RESULT>,

	LENGTH_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	STRING_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	
	NO_PARAM_LENGTH_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, RET>>

	extends ISharedFunctions_String_Alias<MODEL, RESULT, RET, LENGTH_RET, STRING_RET>,
			    ISharedFunctions_String_NoParam_Base<MODEL, RESULT,
			    
			    ISharedMapFunctions_String_Alias<
		    	MODEL, RESULT,
		    	RET,
		    	LENGTH_RET,
		    	LENGTH_RET,
		    	NO_PARAM_LENGTH_RET,
		    	NO_PARAM_LENGTH_RET>, 			    
			    
			    ISharedMapFunctions_String_Alias<
			    	MODEL, RESULT,
			    	RET,
			    	LENGTH_RET,
			    	STRING_RET,
			    	NO_PARAM_LENGTH_RET,
			    	NO_PARAM_STRING_RET
			    
			    >> {
	
}
