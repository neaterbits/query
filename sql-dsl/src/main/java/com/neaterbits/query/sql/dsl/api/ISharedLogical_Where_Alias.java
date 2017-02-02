package com.neaterbits.query.sql.dsl.api;


public interface ISharedLogical_Where_Alias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedLogical_Where<MODEL, RESULT> {
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(ISupplierInteger func);
	
	ISharedFunctions_Alias_Initial<
			MODEL,
			RESULT, CONDITION_CLAUSE,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, CONDITION_CLAUSE>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, CONDITION_CLAUSE>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, CONDITION_CLAUSE>>
		where();
	
    ISharedCondition_Comparable_String_All<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierString supplier);
}
