package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_Numeric_Named<
	MODEL,
	RESULT,
	
	RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
	
	SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	COUNT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	
	SHORT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	INT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
	LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	DOUBLE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,

	NO_PARAM_SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_COUNT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	
	
	NO_PARAM_SHORT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_INT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
	NO_PARAM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_DOUBLE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>>

	extends 
	
		ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET>,
		ISharedFunctions_Aggregate_Named<SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET>,
	
		ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT,
		
			ISharedMapFunctions_Numeric_Named<
			
					MODEL, RESULT, RET,
			
					//SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET,
			
					// Pass NO_PARAM types for both recursively, since cannot use .plus() when nested functions in this format
					NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET,
					NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET
					>,
		
			// for sqrt()
			
			ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET,
			
				NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET,
				NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET>
		>
		
		{

}
