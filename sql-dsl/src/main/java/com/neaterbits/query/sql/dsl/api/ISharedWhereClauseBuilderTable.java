package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClauseBuilderTable<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<T, E extends Enum<E>> ISharedConditionClause<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

	<T> ISharedConditionClause<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(IFunctionInteger<T> func);

    <T> ISharedStringClause<MODEL, RESULT, CONDITION_CLAUSE> where(StringFunction<T> func);

}
