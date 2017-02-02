package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Where_Named_Base<
					MODEL,
					RESULT,
					CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>,
					
					INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, CONDITION_CLAUSE>,
					LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, CONDITION_CLAUSE>,
					STRING_CLAUSE  extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, CONDITION_CLAUSE>>

	extends ISharedLogical_Where<MODEL, RESULT> {

	<T> INTEGER_CLAUSE where(IFunctionInteger<T> func);

	<T> LONG_CLAUSE where(IFunctionLong<T> func);

	<T> STRING_CLAUSE where(StringFunction<T> func);
    
	ISharedFunctions_Named_Initial<MODEL, RESULT, CONDITION_CLAUSE, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> where();

}
