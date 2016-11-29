package com.neaterbits.query.sql.dsl.api;


public interface ComparativeClause<MODEL, RESULT, R extends Comparable<R>, L extends LogicalClauses<MODEL, RESULT>> 
		extends ConditionClause<MODEL, RESULT, R, L> {

	LogicalClauses<MODEL, RESULT> isGreaterThan(R value);

	LogicalClauses<MODEL, RESULT> isGreaterThan(Param<R> value);

	LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(R value);

	LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Param<R> value);

	LogicalClauses<MODEL, RESULT> isLesserThan(R value);

	LogicalClauses<MODEL, RESULT> isLesserThan(Param<R> value);

	LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(R value);

	LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Param<R> value);

}

