package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClausesTableValue<
			MODEL,
			RESULT,
			CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>>

		extends ISharedWhereClauseBuilderTableBase<MODEL, RESULT, CONDITION_CLAUSE,

		ISharedClauseComparableCommonValue<MODEL,RESULT,Integer,CONDITION_CLAUSE>,
		ISharedClauseComparableStringValue<MODEL,RESULT,CONDITION_CLAUSE>>{

		<T, E extends Enum<E>> ISharedClauseConditionValue<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

}

		