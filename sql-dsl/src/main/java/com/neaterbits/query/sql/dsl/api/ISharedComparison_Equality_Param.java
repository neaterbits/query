package com.neaterbits.query.sql.dsl.api;

public interface ISharedComparison_Equality_Param<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedComparison_Basic<MODEL, RESULT, R, L> {

	L isEqualTo(ValParam<R> other);

	L isNotEqualTo(ValParam<R> other);
	
	L in(InParam<R> param);
	
}
