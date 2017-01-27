package com.neaterbits.query.sql.dsl.api;


public interface ISharedLogical_Where_Alias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedLogical_Where<MODEL, RESULT> {
	
	ISharedCondition_Equality_All<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(ISupplierInteger func);
	
    ISharedCondition_Comparable_String_All<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierString supplier);
}
