package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparativeStringAll<MODEL, RESULT, L extends ISharedLogicalClauses<MODEL, RESULT>>
		extends ISharedClauseComparativeBaseAll<MODEL, RESULT, String, L>,
				ISharedClauseComparativeStringValue<MODEL, RESULT, L>,
				ISharedClauseComparativeStringParam<MODEL, RESULT, L> {

}
