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
		
		
		NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_BIGDECIMAL_RET,
		ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGDECIMAL_RET,
		
		
		NAMED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		
		
		/* Not comparables, we just map to ResultMapperTo for all
		NAMED_INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, NAMED_RET>,
		NAMED_LONG_CLAUSE, //  extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, NAMED_RET>,
		NAMED_STRING_CLAUSE, //  extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, NAMED_RET>,

		ALIAS_INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, ALIAS_RET>,
		ALIAS_LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, ALIAS_RET>,
		ALIAS_STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, ALIAS_RET>
		*/

		NAMED_INTEGER_CLAUSE extends ISharedResultMapperTo<MODEL, RESULT, Integer, NAMED_RET>,
		NAMED_LONG_CLAUSE    extends ISharedResultMapperTo<MODEL, RESULT, Long,    NAMED_RET>,
		NAMED_STRING_CLAUSE  extends ISharedResultMapperTo<MODEL, RESULT, String,  NAMED_RET>,

		ALIAS_INTEGER_CLAUSE extends ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_RET>,
		ALIAS_LONG_CLAUSE 	 extends ISharedResultMapperTo<MODEL, RESULT, Long,    ALIAS_RET>,
		ALIAS_STRING_CLAUSE  extends ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_RET>
		>

	extends 
	
		// named without no-param (because of signature collision)
		ISharedFunctions_Named_Initial<MODEL, RESULT, NAMED_RET, NAMED_INTEGER_CLAUSE, NAMED_LONG_CLAUSE, NAMED_STRING_CLAUSE>,
		
		// alias without no-param (because of signature collision)
		ISharedFunctions_Alias_Initial<MODEL, RESULT, ALIAS_RET, ALIAS_INTEGER_CLAUSE, ALIAS_LONG_CLAUSE, ALIAS_STRING_CLAUSE>, 

		// Aggregates as well 
		IShared_Aggregate_All_Named<NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_BIGDECIMAL_RET>,
		
		IShared_Aggregate_All_Alias<ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGDECIMAL_RET>
		
		
		{
	

}
