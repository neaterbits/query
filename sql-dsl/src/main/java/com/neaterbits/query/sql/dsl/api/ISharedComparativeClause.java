package com.neaterbits.query.sql.dsl.api;


public interface ISharedComparativeClause<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogicalClauses<MODEL, RESULT>> 
		extends ISharedConditionClause<MODEL, RESULT, R, L> {

	L isGreaterThan(R value);

	L isGreaterThan(Param<R> value);

	L isGreaterOrEqualTo(R value);

	L isGreaterOrEqualTo(Param<R> value);

	L isLesserThan(R value);

	L isLesserThan(Param<R> value);

	L isLesserOrEqualTo(R value);

	L isLesserOrEqualTo(Param<R> value);

}

