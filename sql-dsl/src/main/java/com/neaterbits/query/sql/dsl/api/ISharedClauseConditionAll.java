package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseConditionAll<MODEL, RESULT, R, L extends ISharedLogicalClauses<MODEL, RESULT>> 

		extends ISharedClauseConditionValue<MODEL, RESULT, R, L>,
				ISharedClauseConditionParam<MODEL, RESULT, R, L> {

}
