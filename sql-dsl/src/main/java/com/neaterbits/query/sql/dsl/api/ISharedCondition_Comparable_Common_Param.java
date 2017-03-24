package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_Comparable_Common_Param<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO Comparable<R>
			L extends ISharedLogical_Base<MODEL, RESULT>> 
		
	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, R, L>,
			ISharedCondition_Equality_Param<MODEL, RESULT, R, L> {

	L isGreaterThan(ValParam<R> value);
	
	L isGreaterOrEqualTo(ValParam<R> value);
	
	L isLessThan(ValParam<R> value);
	
	L isLesserOrEqualTo(ValParam<R> value);
}