package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_Numeric_Alias<
	MODEL,
	RESULT,
	
	RET extends ISharedFunction_After<MODEL, RESULT>,
	
	SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	COUNT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	
	BYTE_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	SHORT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	INT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
	LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	BIGINTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	FLOAT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	DOUBLE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	DATE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	SQLDATE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	SQLTIME_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	
	NO_PARAM_SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_COUNT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	
	
	NO_PARAM_BYTE_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_SHORT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_INT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
	NO_PARAM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_BIGINTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_FLOAT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_DOUBLE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_DATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_SQLDATE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_SQLTIME_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	NO_PARAM_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	>
	
	extends 
	
		ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET>,
		ISharedFunctions_Aggregate_Alias<SUM_LONG_RET, COUNT_RET, BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, DATE_RET>,
	
		ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT,
		
			ISharedMapFunctions_Numeric_Alias<
			
					MODEL, RESULT, RET,
			
					//SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET,
			
					// Pass NO_PARAM types for both recursively, since cannot use .plus() when nested functions in this format
					NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_BYTE_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_BIGINTEGER_RET, NO_PARAM_FLOAT_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET, NO_PARAM_DATE_RET, NO_PARAM_CALENDAR_RET, NO_PARAM_SQLDATE_RET, NO_PARAM_SQLTIME_RET, NO_PARAM_SQLTIMESTAMP_RET,
					NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_BYTE_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_BIGINTEGER_RET, NO_PARAM_FLOAT_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET, NO_PARAM_DATE_RET, NO_PARAM_CALENDAR_RET, NO_PARAM_SQLDATE_RET, NO_PARAM_SQLTIME_RET, NO_PARAM_SQLTIMESTAMP_RET
					>,
		
			// for sqrt()
			
			ISharedMapFunctions_Numeric_Alias<MODEL, RESULT, RET,
			
				NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET,
				NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET>
		>
	
	{

}
