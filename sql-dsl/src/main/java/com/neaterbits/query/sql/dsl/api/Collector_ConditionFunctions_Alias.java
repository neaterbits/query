package com.neaterbits.query.sql.dsl.api;


class Collector_ConditionFunctions_Alias<

		MODEL,
		RESULT,
		
		// commented out since reused for mapping functions 
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SHORT_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET    	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGINTEGER_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		FLOAT_RET    	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		CALENDAR_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLDATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
		>

	extends Collector_NestedFunctions_Alias<
			MODEL, 
			RESULT, 
			RET,

			ISharedFunction_Next<MODEL, RESULT, RET>,
			ISharedFunction_Next<MODEL, RESULT, RET>,
		
			BYTE_RET,
			SHORT_RET,
			INTEGER_RET,
			LONG_RET,
			BIGINTEGER_RET,
			FLOAT_RET,
			DOUBLE_RET,
			BIGDECIMAL_RET,
			STRING_RET,
			DATE_RET,
			CALENDAR_RET,
			SQLDATE_RET,
			SQLTIME_RET,
			SQLTIMESTAMP_RET,
			
			ISharedFunction_Next<MODEL, RESULT, RET>,
			ISharedFunction_Next<MODEL, RESULT, RET>,
			ISharedFunction_Next<MODEL, RESULT, RET>> 

	//implements ISharedFunctions_Initial_And_NoParam_Alias<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET> 

{
	
	Collector_ConditionFunctions_Alias(ISharedCollector_Functions_Callback<MODEL, RESULT, RET> func) {
		super(func);
	}
}
