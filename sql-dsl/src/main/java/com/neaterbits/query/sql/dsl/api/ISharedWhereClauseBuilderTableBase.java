package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClauseBuilderTableBase<
					MODEL,
					RESULT,
					CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>,
					
					INTEGER_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, ?, CONDITION_CLAUSE>,
					STRING_CLAUSE  extends ISharedClauseComparableStringBase<MODEL, RESULT, CONDITION_CLAUSE>>

	extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<T> INTEGER_CLAUSE where(IFunctionInteger<T> func);

    <T> STRING_CLAUSE where(StringFunction<T> func);

}
