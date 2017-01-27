package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Where_Named_Base<
					MODEL,
					RESULT,
					CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>,
					
					COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, CONDITION_CLAUSE>,
					STRING_CLAUSE  extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, CONDITION_CLAUSE>>

	extends ISharedLogical_Where<MODEL, RESULT> {

	<T> COMPARABLE_CLAUSE where(IFunctionInteger<T> func);

	<T> COMPARABLE_CLAUSE where(IFunctionLong<T> func);

	<T> STRING_CLAUSE where(StringFunction<T> func);
    
	ISharedFunctions_Named_Initial<MODEL, RESULT, CONDITION_CLAUSE, COMPARABLE_CLAUSE, STRING_CLAUSE> where();

}
