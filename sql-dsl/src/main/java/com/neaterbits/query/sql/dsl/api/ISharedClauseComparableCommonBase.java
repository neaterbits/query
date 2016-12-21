package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparableCommonBase<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO : Comparable<R>
			L extends ISharedLogicalClauses<MODEL, RESULT>>

		extends ISharedClauseConditionBase<MODEL, RESULT, R, L> {

}
