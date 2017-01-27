package com.neaterbits.query.sql.dsl.api;


public interface ISharedLogical_And_Alias_Base<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
		extends ISharedLogical_And_Alias<MODEL, RESULT> {

	ISharedCondition_Equality_Alias<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter);

	ISharedCondition_Equality_Alias<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter);

    ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter);

	AND_CLAUSES andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder);
    
}
