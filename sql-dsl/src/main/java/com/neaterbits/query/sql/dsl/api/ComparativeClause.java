package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ComparativeClause<MODEL, RESULT, R extends Comparable<R>, L extends LogicalClauses<MODEL, RESULT>> 
		extends ConditionClause<MODEL, RESULT, R, L> {


	LogicalClauses<MODEL, RESULT> isGreaterThan(R value);

	<T> LogicalClauses<MODEL, RESULT> isGreaterThan(Function<T, R> getter);
	
	LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(RESULT value);

	<T> LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Function<T, R> getter);
	
	LogicalClauses<MODEL, RESULT> isLesserThan(R value);

	<T> LogicalClauses<MODEL, RESULT> isLesserThan(Function<T, R> getter);
	
	LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(R value);

	<T> LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Function<T, R> getter);
}
