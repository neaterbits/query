package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseConditionValue<MODEL, RESULT, R, L extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedClauseConditionBase<MODEL, RESULT, R, L> {
	
	L isEqualTo(R other);

	L isNotEqualTo(R other);

	L in(@SuppressWarnings("unchecked") R ... values);
	
}
