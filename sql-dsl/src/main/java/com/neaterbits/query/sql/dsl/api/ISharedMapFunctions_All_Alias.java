package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_All_Alias<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COUNT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		
		SHORT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
		LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		NO_PARAM_SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_COUNT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		NO_PARAM_SHORT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_INT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
		NO_PARAM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_DOUBLE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_STRING_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
		>
		
		extends
		
			ISharedMapFunctions_Numeric_Alias<MODEL, RESULT, RET,
			
				SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET,
				NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET>,
		
			ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET>,
			
			ISharedFunctions_String_NoParam_Base<MODEL, RESULT, NO_PARAM_STRING_RET> {
}