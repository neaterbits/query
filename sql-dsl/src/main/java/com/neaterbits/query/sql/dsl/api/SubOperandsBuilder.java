package com.neaterbits.query.sql.dsl.api;

abstract class SubOperandsBuilder<MODEL,

			RESULT,
			R extends Comparable<R>,

			
			AFTER extends ISharedFunction_After<MODEL,RESULT>,

			SUM_LONG_RET extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			COUNT_RET extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			
			SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			LONG_RET	 extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			DOUBLE_RET	 extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			
			NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
			NO_PARAM_ARITHMETIC_DOUBLE_RET,
			
			NO_PARAM_STRING_RET>

		extends Collector_NestedFunctions_Named<
		
		
			MODEL,
			RESULT,
			
			AFTER,
			
			SUM_LONG_RET,
			COUNT_RET,
			
			SHORT_RET,
			INTEGER_RET,
			LONG_RET,
			DOUBLE_RET,
			BIGDECIMAL_RET,
			STRING_RET,
			
			NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
			NO_PARAM_ARITHMETIC_DOUBLE_RET,
			
			NO_PARAM_STRING_RET
			
			>

{

	SubOperandsBuilder(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AFTER> func /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		super(func);
		//super(func, last);
	}

	SubOperandsBuilder(SubOperandsBuilder<MODEL, RESULT, R, AFTER, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
		super(toCopy);
		//super(func, last);
	}
	
}
