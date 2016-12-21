package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedWhereClauseBuilderAlias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedWhereClauseBuilder<MODEL, RESULT> {
	
	ISharedClauseConditionAll<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(ISupplierInteger func);
	
    ISharedClauseComparativeStringAll<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierString supplier);
}
