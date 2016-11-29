package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ComparativeClauseTable<MODEL, RESULT, R extends Comparable<R>, L extends LogicalClauses<MODEL, RESULT>>
	extends ComparativeClause<MODEL, RESULT, R, L>,
	        ConditionClauseTable<MODEL, RESULT, R, L> {
	
	<T> LogicalClauses<MODEL, RESULT> isGreaterThan(Function<T, R> getter);
	
	<T> LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Function<T, R> getter);
	
	<T> LogicalClauses<MODEL, RESULT> isLesserThan(Function<T, R> getter);
	
	<T> LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Function<T, R> getter);
	
}
