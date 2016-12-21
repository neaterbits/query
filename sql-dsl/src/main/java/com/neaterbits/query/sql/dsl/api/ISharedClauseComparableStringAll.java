package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparableStringAll<MODEL, RESULT, L extends ISharedLogicalClauses<MODEL, RESULT>>
		extends ISharedClauseComparableCommonAll<MODEL, RESULT, String, L>,
				ISharedClauseComparableStringValue<MODEL, RESULT, L>,
				ISharedClauseComparableStringParam<MODEL, RESULT, L> {

}
