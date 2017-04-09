package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedLogical_Or_Alias_Base<
		MODEL,
		RESULT,
		OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Alias<MODEL, RESULT>
		> extends ISharedLogical_Or_Alias<MODEL, RESULT> {
	
    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter);

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter);
    
    ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES> or(ISupplierBigDecimal getter);

    ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter);
    
	OR_CLAUSES orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, NESTED_AND_CLAUSES> andBuilder);
    
}
