package com.neaterbits.query.sql.dsl.api;


public interface ISharedComparativeClause<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogicalClauses<MODEL, RESULT>> 
		extends ISharedConditionClause<MODEL, RESULT, R, L> {

	ISharedLogicalClauses<MODEL, RESULT> isGreaterThan(R value);

	ISharedLogicalClauses<MODEL, RESULT> isGreaterThan(Param<R> value);

	ISharedLogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(R value);

	ISharedLogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Param<R> value);

	ISharedLogicalClauses<MODEL, RESULT> isLesserThan(R value);

	ISharedLogicalClauses<MODEL, RESULT> isLesserThan(Param<R> value);

	ISharedLogicalClauses<MODEL, RESULT> isLesserOrEqualTo(R value);

	ISharedLogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Param<R> value);

}

