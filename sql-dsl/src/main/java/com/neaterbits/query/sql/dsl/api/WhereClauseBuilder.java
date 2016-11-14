package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface WhereClauseBuilder<MODEL, RESULT> {

	<T, R> ConditionClause<MODEL, RESULT, R, AndOrLogicalClauses<MODEL, RESULT>> where(Function<T, R> func);
	
    <T> StringClause<MODEL, RESULT, AndOrLogicalClauses<MODEL, RESULT>> where(StringFunction<T> func);
}
