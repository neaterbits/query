package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClauseBuilderTable<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>> {

	<T, E extends Enum<E>> ISharedConditionClause<MODEL, RESULT, E, CONDITION_CLAUSE> where(EnumFunction<T, E> func);

	<T> ISharedConditionClause<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(IntegerFunction<T> func);

    <T> ISharedStringClause<MODEL, RESULT, CONDITION_CLAUSE> where(StringFunction<T> func);

}
