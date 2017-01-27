package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_Equality_Value<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedCondition_Base<MODEL, RESULT, R, L> {
	
	L isEqualTo(R other);

	L isNotEqualTo(R other);

	L in(@SuppressWarnings("unchecked") R ... values);
	
}
