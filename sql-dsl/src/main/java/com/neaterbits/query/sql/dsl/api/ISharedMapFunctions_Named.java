package com.neaterbits.query.sql.dsl.api;

public interface ISharedMapFunctions_Named<
		MODEL,
		RESULT,

		NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_BIGDECIMAL_RET,

		NAMED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,

		// TODO: cannot map to ResultMapperTo, must allow for shared-functons as well
		
		NAMED_INTEGER_CLAUSE extends ISharedResultMapperTo<MODEL, RESULT, Integer, NAMED_RET>,
		NAMED_LONG_CLAUSE    extends ISharedResultMapperTo<MODEL, RESULT, Long,    NAMED_RET>,
		NAMED_STRING_CLAUSE  extends ISharedResultMapperTo<MODEL, RESULT, String,  NAMED_RET>
		>
		
		extends 
		
		// named without no-param (because of signature collision)
		ISharedFunctions_Named_All<MODEL, RESULT, NAMED_RET, NAMED_INTEGER_CLAUSE, NAMED_LONG_CLAUSE, NAMED_STRING_CLAUSE>,
		
		// Aggregates as well 
		IShared_Aggregate_All_Named<NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_BIGDECIMAL_RET>


{

}
