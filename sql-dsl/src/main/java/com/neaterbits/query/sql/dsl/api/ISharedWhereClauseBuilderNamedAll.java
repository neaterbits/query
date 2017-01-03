package com.neaterbits.query.sql.dsl.api;

public interface ISharedWhereClauseBuilderNamedAll<
			MODEL,
			RESULT,
			CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>,
			
			COMPARABLE_CLAUSE extends ISharedClauseComparableCommonAll<MODEL,RESULT,? extends Comparable<?>,CONDITION_CLAUSE>,
		    STRING_CLAUSE extends ISharedClauseComparableStringAll<MODEL,RESULT,CONDITION_CLAUSE>>

		extends ISharedWhereClauseBuilderNamedBase<MODEL, RESULT, CONDITION_CLAUSE,
		
			COMPARABLE_CLAUSE,
			STRING_CLAUSE>{

	<T, E extends Enum<E>> ISharedClauseConditionAll<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

	
}
