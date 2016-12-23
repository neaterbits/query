package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparableCommonAll_Compilable<
				MODEL,
				RESULT,
				R extends Comparable<?>, // TODO: Comparable<R>
				L extends ISharedLogicalClauses<MODEL, RESULT>> 
		extends ISharedClauseComparableCommonAll<MODEL, RESULT, R, L> {

}
