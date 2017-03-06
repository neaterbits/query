package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_Alias<
		MODEL,
		RESULT,
		
		ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGDECIMAL_RET,
		
		ALIAS_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		
		// TODO: cannot map to ResultMapperTo, must allow for shared-functions as well
		
		ALIAS_INTEGER_CLAUSE extends ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_RET>,
		ALIAS_LONG_CLAUSE    extends ISharedResultMapperTo<MODEL, RESULT, Long,    ALIAS_RET>,
		ALIAS_STRING_CLAUSE  extends ISharedResultMapperTo<MODEL, RESULT, String,  ALIAS_RET>
		>
		
		extends 
		
		// named without no-param (because of signature collision)
		ISharedFunctions_Alias_All<MODEL, RESULT, ALIAS_RET, ALIAS_INTEGER_CLAUSE, ALIAS_LONG_CLAUSE, ALIAS_STRING_CLAUSE>,
		
		// Aggregates as well 
		ISharedFunctions_Alias_Aggregate<ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGDECIMAL_RET>


{

}
