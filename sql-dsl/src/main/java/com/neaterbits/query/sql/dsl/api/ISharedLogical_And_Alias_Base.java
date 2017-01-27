package com.neaterbits.query.sql.dsl.api;


public interface ISharedLogical_And_Alias_Base<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
		
		extends ISharedLogical_And_Alias<MODEL, RESULT> {

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter);

    ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter);

    ISharedFunctions_Alias_Initial<
    	MODEL,
    	RESULT,
    	AND_CLAUSES,
    	ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, AND_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>>
    	
    		and();
    
	AND_CLAUSES andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder);
    
}
