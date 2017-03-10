package com.neaterbits.query.sql.dsl.api;

abstract class ResultMapper_ExpressionList_Initial_Named< 
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,

		SHORT_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INT_RET	  		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>
		>
	

	extends ResultMapper_ExpressionList_Base<
		MODEL,
		RESULT,

		Integer,
		
		RET,

		RET,
		ISharedFunction_After<MODEL, RESULT>,
		
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer, RET>,
		ISharedResultOps_String_Named<MODEL, RESULT, RET>,

		
		SUM_LONG_RET,
		COUNT_RET,
		
		SHORT_RET,
		INT_RET,
		LONG_RET,
		DOUBLE_RET,
		BIGDECIMAL_RET,
		
		STRING_RET,

		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,

		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>
	>
	
	 {
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_Initial_Named(IMappingCollector<MODEL, RESULT> impl) {
		super(impl);
	}
}
