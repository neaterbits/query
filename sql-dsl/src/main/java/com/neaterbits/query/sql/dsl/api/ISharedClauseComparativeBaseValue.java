package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparativeBaseValue<
		MODEL,
		RESULT,
		R extends Comparable<?>, // TODO : Comparable<R>
		L extends ISharedLogicalClauses<MODEL, RESULT>> 

		extends ISharedClauseComparativeBaseBase<MODEL, RESULT, R, L>,
				ISharedClauseConditionValue<MODEL, RESULT, R, L> {

	L isGreaterThan(R value);

	L isGreaterOrEqualTo(R value);
	
	L isLesserThan(R value);
	
	L isLesserOrEqualTo(R value);
	
}
