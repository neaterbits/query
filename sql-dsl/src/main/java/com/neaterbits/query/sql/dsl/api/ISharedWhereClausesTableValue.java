package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClausesTableValue<
			MODEL,
			RESULT,
			CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>>

		extends ISharedWhereClauseBuilderTable<MODEL, RESULT, CONDITION_CLAUSE,

		ISharedClauseComparativeBaseValue<MODEL,RESULT,Integer,CONDITION_CLAUSE>,
		ISharedClauseComparativeStringValue<MODEL,RESULT,CONDITION_CLAUSE>>{

		<T, E extends Enum<E>> ISharedClauseConditionValue<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

}
