package com.neaterbits.query.sql.dsl.api;


final class Collector_ConditionFunctions_Named<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SHORT_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET	 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGINTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		FLOAT_RET	 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET	  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLDATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	> 

	extends Collector_NestedFunctions_Named<
			MODEL, RESULT, 
			
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
			ISharedFunction_Next<MODEL, RESULT, RET>
			>

	 {

	Collector_ConditionFunctions_Named(ISharedCollector_Functions_Callback<MODEL, RESULT, RET> func) {
		super(func);
	}

	
	/*
	@Override
	<R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function,
			ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub) {
		
		throw new UnsupportedOperationException("TODO - sub for conditions");
	}



	@Override
	<CLAUSE> CLAUSE addSubString(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub) {

		throw new UnsupportedOperationException("TODO - sub for conditions");
	}
	*/


	/*
	@Override
	public ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, INTEGER_RET, LONG_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
	*/
	
}
