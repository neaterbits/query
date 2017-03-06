package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_Named<
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
		STRING_RET extends ISharedFunction_Next<MODEL, RESULT, RET>

		>
		
		extends
		
			ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET, SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET>,

			ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_RET>,
			ISharedFunctions_String_NoParam_Named<MODEL, RESULT, RET, STRING_RET> {

}
