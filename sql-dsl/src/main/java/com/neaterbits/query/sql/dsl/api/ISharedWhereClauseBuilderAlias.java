package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedWhereClauseBuilderAlias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedWhereClauseBuilder<MODEL, RESULT> {
	
	<R> ISharedConditionClause<MODEL, RESULT, R, CONDITION_CLAUSE> where(Supplier<R> func);
	
    ISharedStringClause<MODEL, RESULT, CONDITION_CLAUSE> where(StringSupplier supplier);
}
