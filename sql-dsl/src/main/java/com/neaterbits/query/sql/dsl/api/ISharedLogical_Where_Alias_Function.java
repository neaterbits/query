package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Where_Alias_Function<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>> {
	ISharedFunctions_Transform_Initial_Alias<
	MODEL,
	RESULT, CONDITION_CLAUSE,
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, CONDITION_CLAUSE>,
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, CONDITION_CLAUSE>,
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, CONDITION_CLAUSE>,
	ISharedCondition_Comparable_String_All<MODEL, RESULT, CONDITION_CLAUSE>>
	
		where();

}
