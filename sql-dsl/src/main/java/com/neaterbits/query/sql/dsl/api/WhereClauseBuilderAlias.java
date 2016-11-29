package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface WhereClauseBuilderAlias<MODEL, RESULT> {

	<R> ConditionClause<MODEL, RESULT, R, AndOrLogicalClausesAlias<MODEL, RESULT>> where(Supplier<R> func);
	
    StringClause<MODEL, RESULT, AndOrLogicalClausesAlias<MODEL, RESULT>> where(StringSupplier supplier);

}
