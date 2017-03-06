package com.neaterbits.query.sql.dsl.api;


/**
 * All functions that can be included in an initial map(), before we decide whether
 * is named or alias based
 * 
 * @author nhl
 *
 */

public interface ISharedMapFunctions_Initial<
		MODEL,
		RESULT,

		NAMED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,

		NAMED_SUM_LONG_RET 	 extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET 	 extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET 	 extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INT_RET 		 extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET 		 extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET 	 extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

		ALIAS_SUM_LONG_RET	 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET		 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET		 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INT_RET		 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET		 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET 	 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
		>

	extends 
	
		// named without no-param (because of signature collision)
		ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, NAMED_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_STRING_RET>,
		
		// alias without no-param (because of signature collision)
		ISharedFunctions_Transform_Initial_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_STRING_RET>, 

		// Aggregates as well 
		ISharedFunctions_Aggregate_Named<NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_BIGDECIMAL_RET>,
		ISharedFunctions_Aggregate_Alias<ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGDECIMAL_RET>
		
		
		// And with no-param, but must avoid name-crash so passing base once, that will return self if called
		
		//ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT, ISharedFunctions_Arithmetic_NoParam_>,
		//ISharedFunctions_String_NoParam_Base<MODEL, RESULT, ISharedFunctions_String_NoParam_Base<MODEL, RESULT, ?>>
		
		
		// ISharedFunctions_Named_String_NoParam<MODEL, RESULT, ALIAS_RET, ALIAS_STRING_RET>,
		// ISharedFunctions_Alias_String_NoParam<MODEL, RESULT, ALIAS_RET, ALIAS_STRING_RET>
		
		
		{
	

}
