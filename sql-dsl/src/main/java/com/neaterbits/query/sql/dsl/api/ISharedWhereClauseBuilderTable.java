package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClauseBuilderTable<
					MODEL,
					RESULT,
					CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>,
					
					INTEGER_CLAUSE extends ISharedClauseComparativeBaseBase<MODEL, RESULT, Integer, CONDITION_CLAUSE>,
					STRING_CLAUSE  extends ISharedClauseComparativeBaseBase<MODEL, RESULT, String, CONDITION_CLAUSE>>

	extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<T> INTEGER_CLAUSE where(IFunctionInteger<T> func);

    <T> STRING_CLAUSE where(StringFunction<T> func);

}
