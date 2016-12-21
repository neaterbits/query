package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparativeBaseParam<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO Comparable<R>
			L extends ISharedLogicalClauses<MODEL, RESULT>> 
		
	extends ISharedClauseComparativeBaseBase<MODEL, RESULT, R, L>,
			ISharedClauseConditionParam<MODEL, RESULT, R, L> {

	L isGreaterThan(Param<R> value);
	
	L isGreaterOrEqualTo(Param<R> value);
	
	L isLesserThan(Param<R> value);
	
	L isLesserOrEqualTo(Param<R> value);
}