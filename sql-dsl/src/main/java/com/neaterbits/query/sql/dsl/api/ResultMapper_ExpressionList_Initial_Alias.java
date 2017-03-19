package com.neaterbits.query.sql.dsl.api;

abstract class ResultMapper_ExpressionList_Initial_Alias< 
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
			
			ISharedFunction_After<MODEL, RESULT>,
			RET,
			
			ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Integer, RET>,
			ISharedResultOps_String_Alias<MODEL, RESULT, RET>,

			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			
			SUM_LONG_RET,
			COUNT_RET,
			
			SHORT_RET,
			INT_RET,
			LONG_RET,
			DOUBLE_RET,
			BIGDECIMAL_RET,
			
			STRING_RET
			
		>

	{

	private final IMappingCollector<MODEL, RESULT> impl;
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_Initial_Alias(IMappingCollector<MODEL, RESULT> impl) {
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.impl = impl;
	}
	
	@Override
	IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType) {
		if (fieldAccessType != EFieldAccessType.ALIAS) {
			throw new IllegalStateException("Expected alias");
		}
		
		return impl;
	}
}
