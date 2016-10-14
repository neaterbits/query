package com.neaterbits.query.sql.dsl.api;

import com.google.inject.internal.Function;

public interface SelectCondition<MODEL, RESULT> {

	<T, R> ConditionClause<MODEL, RESULT, R, AndOrLogicalClauses<MODEL, RESULT>> where(Function<T, R> func);
	
    <T> StringClause<MODEL, RESULT, AndOrLogicalClauses<MODEL, RESULT>> where(StringFunction<T> func);
}
