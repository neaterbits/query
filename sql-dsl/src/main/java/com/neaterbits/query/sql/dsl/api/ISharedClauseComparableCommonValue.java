package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparableCommonValue<
		MODEL,
		RESULT,
		R extends Comparable<?>, // TODO : Comparable<R>
		L extends ISharedLogicalClauses<MODEL, RESULT>> 

		extends ISharedClauseComparableCommonBase<MODEL, RESULT, R, L>,
				ISharedClauseConditionValue<MODEL, RESULT, R, L> {

	L isGreaterThan(R value);

	L isGreaterOrEqualTo(R value);
	
	L isLessThan(R value);
	
	L isLessOrEqualTo(R value);
	
}
