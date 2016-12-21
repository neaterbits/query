package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClauseBuilderTableAll<
			MODEL,
			RESULT,
			CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>>

		extends ISharedWhereClauseBuilderTable<MODEL, RESULT, CONDITION_CLAUSE,
		
			ISharedClauseComparableCommonAll<MODEL,RESULT,Integer,CONDITION_CLAUSE>,
			ISharedClauseComparableStringAll<MODEL,RESULT,CONDITION_CLAUSE>>{

	<T, E extends Enum<E>> ISharedClauseConditionAll<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

	
}
