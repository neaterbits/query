package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_Named<
		MODEL,
		RESULT,

		RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		
		SUM_LONG_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COUNT_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		
		SHORT_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INT_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
		LONG_NEXT	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_NEXT extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_NEXT extends ISharedFunction_Next<MODEL, RESULT, RET>

		>
		
		extends
		
			ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET, SUM_LONG_NEXT, COUNT_NEXT, SHORT_NEXT, INT_NEXT, LONG_NEXT, BIGDECIMAL_NEXT>,

			ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_NEXT>,
			ISharedFunctions_String_NoParam_Named<MODEL, RESULT, RET, STRING_NEXT> {

}
