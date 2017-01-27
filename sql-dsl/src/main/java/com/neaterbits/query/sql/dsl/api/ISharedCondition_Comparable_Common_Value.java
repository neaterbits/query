package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_Comparable_Common_Value<
		MODEL,
		RESULT,
		R extends Comparable<?>, // TODO : Comparable<R>
		L extends ISharedLogical_Base<MODEL, RESULT>> 

		extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, R, L>,
				ISharedCondition_Equality_Value<MODEL, RESULT, R, L> {

	L isGreaterThan(R value);

	L isGreaterOrEqualTo(R value);
	
	L isLessThan(R value);
	
	L isLessOrEqualTo(R value);
	
}
