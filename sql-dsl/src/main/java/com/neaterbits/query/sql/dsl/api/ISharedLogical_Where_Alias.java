package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedLogical_Where_Alias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedLogical_Where<MODEL, RESULT> {
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(ISupplierInteger func);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, CONDITION_CLAUSE> where(ISupplierBigDecimal func);
	
    ISharedCondition_Comparable_String_All<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierString supplier);
}
