package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_Named<
		MODEL,
		RESULT,

		RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		
		SUM_LONG_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COUNT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		
		SHORT_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INT_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
		LONG_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_NEXT extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_NEXT extends ISharedFunction_Next<MODEL, RESULT, RET>

		>
		
		extends 
		
		// named without no-param (because of signature collision)
		ISharedFunctions_Named_All<MODEL, RESULT, RET, INT_NEXT, LONG_NEXT, STRING_NEXT>,
		
		// Aggregates as well 
		ISharedFunctions_Named_Aggregate<SUM_LONG_NEXT, COUNT_RET, SHORT_NEXT, INT_NEXT, LONG_NEXT, BIGDECIMAL_NEXT>


{

}
