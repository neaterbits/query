package com.neaterbits.query.sql.dsl.api;


public interface ISharedWhereClauseBuilderAlias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedWhereClauseBuilder<MODEL, RESULT> {
	
	ISharedClauseConditionAll<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(ISupplierInteger func);
	
    ISharedClauseComparableStringAll<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierString supplier);
}
