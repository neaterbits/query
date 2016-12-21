package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedWhereClauseBuilderAlias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedWhereClauseBuilder<MODEL, RESULT> {
	
	ISharedConditionClause<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(ISupplierInteger func);
	
    ISharedStringClause<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierString supplier);
}
