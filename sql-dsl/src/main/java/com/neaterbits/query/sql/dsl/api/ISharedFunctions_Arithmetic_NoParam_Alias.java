package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_NoParam_Alias<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,

		INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET	   extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET	   extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		> 
	extends ISharedFunctions_Arithmetic_NoParam_Base<
			MODEL,
			RESULT,
			ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, INTEGER_RET, LONG_RET, DOUBLE_RET>,
			
			// for sqrt()
			ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET>
	> {

}
