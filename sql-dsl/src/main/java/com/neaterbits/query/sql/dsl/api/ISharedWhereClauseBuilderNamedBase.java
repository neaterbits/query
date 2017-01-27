package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClauseBuilderNamedBase<
					MODEL,
					RESULT,
					CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>,
					
					COMPARABLE_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, ?, CONDITION_CLAUSE>,
					STRING_CLAUSE  extends ISharedClauseComparableStringBase<MODEL, RESULT, CONDITION_CLAUSE>>

	extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<T> COMPARABLE_CLAUSE where(IFunctionInteger<T> func);

	<T> COMPARABLE_CLAUSE where(IFunctionLong<T> func);

	<T> STRING_CLAUSE where(StringFunction<T> func);
    
	ISharedFunctionsNamedInitial<MODEL, RESULT, CONDITION_CLAUSE, COMPARABLE_CLAUSE, STRING_CLAUSE> where();

}
