package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseConditionParam<MODEL, RESULT, R, L extends ISharedLogicalClauses<MODEL, RESULT>>
	extends ISharedClauseConditionBase<MODEL, RESULT, R, L> {

	L isEqualTo(Param<R> other);

	L isNotEqualTo(Param<R> other);
	
}
