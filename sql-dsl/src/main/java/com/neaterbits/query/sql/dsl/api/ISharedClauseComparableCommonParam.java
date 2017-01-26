package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparableCommonParam<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO Comparable<R>
			L extends ISharedLogicalClauses<MODEL, RESULT>> 
		
	extends ISharedClauseComparableCommonBase<MODEL, RESULT, R, L>,
			ISharedClauseConditionParam<MODEL, RESULT, R, L> {

	L isGreaterThan(ValParam<R> value);
	
	L isGreaterOrEqualTo(ValParam<R> value);
	
	L isLesserThan(ValParam<R> value);
	
	L isLesserOrEqualTo(ValParam<R> value);
}