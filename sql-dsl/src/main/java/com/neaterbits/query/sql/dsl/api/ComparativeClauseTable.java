package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ComparativeClauseTable<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedComparativeClause<MODEL, RESULT, R, L>,
	        ISharedConditionClauseTable<MODEL, RESULT, R, L> {
	
	<T> ISharedLogicalClauses<MODEL, RESULT> isGreaterThan(Function<T, R> getter);
	
	<T> ISharedLogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Function<T, R> getter);
	
	<T> ISharedLogicalClauses<MODEL, RESULT> isLesserThan(Function<T, R> getter);
	
	<T> ISharedLogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Function<T, R> getter);
	
}
