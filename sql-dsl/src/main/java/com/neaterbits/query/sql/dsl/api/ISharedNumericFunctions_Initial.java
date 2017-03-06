package com.neaterbits.query.sql.dsl.api;

public interface ISharedNumericFunctions_Initial<

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
		
		ALIAS_SUM_LONG_RET	 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET		 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET		 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INT_RET		 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET		 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
	>

		extends
		
				ISharedFunctions_Arithmetic_Named<MODEL, RESULT, NAMED_RET, NAMED_INT_RET, NAMED_LONG_RET>,
				ISharedFunctions_Aggregate_Named<NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_BIGDECIMAL_RET>,

				ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_INT_RET, ALIAS_LONG_RET>,
				ISharedFunctions_Aggregate_Alias<ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGDECIMAL_RET>,
				
				
				// For recursive no-params - but only to other numeric functions, not to string ones 
				ISharedFunctions_Arithmetic_NoParam_Base<
					MODEL, RESULT, 
					ISharedNumericFunctions_Initial<
							MODEL, 
							RESULT,
							
							NAMED_RET,
							ALIAS_RET,
									
							NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_BIGDECIMAL_RET,
							ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGDECIMAL_RET>>
				
				
{

}