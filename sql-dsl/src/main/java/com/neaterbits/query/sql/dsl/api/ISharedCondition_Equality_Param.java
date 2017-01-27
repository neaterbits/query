package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_Equality_Param<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedCondition_Base<MODEL, RESULT, R, L> {

	L isEqualTo(ValParam<R> other);

	L isNotEqualTo(ValParam<R> other);
	
	L in(InParam<R> param);
	
}
