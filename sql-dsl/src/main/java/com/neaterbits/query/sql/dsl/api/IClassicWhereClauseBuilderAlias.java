package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface IClassicWhereClauseBuilderAlias<MODEL, RESULT> {

	<R> ISharedConditionClause<MODEL, RESULT, R, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(Supplier<R> func);
	
    ISharedStringClause<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(StringSupplier supplier);

}
