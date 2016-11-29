package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface WhereClauseBuilderTable<MODEL, RESULT> extends WhereClauseBuilder<MODEL, RESULT> {

	<T, R> ConditionClause<MODEL, RESULT, R, AndOrLogicalClausesTable<MODEL, RESULT>> where(Function<T, R> func);
	
    <T> StringClause<MODEL, RESULT, AndOrLogicalClausesTable<MODEL, RESULT>> where(StringFunction<T> func);
	
}
