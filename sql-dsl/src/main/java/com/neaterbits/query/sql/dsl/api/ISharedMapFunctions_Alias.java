package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_Alias<
		MODEL,
		RESULT,

		RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,

		SUM_LONG_RET,
		COUNT_RET,

		SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INT_RET		 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>
	>

	extends 

		// named without no-param (because of signature collision)
		ISharedFunctions_All_Transform_Alias<MODEL, RESULT, RET, INT_RET, LONG_RET, DOUBLE_RET, STRING_RET>,

		// Aggregates as well 
		ISharedFunctions_Aggregate_Alias<SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET>

{

}
