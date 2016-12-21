package com.neaterbits.query.sql.dsl.api;


public interface ISharedClauseComparableCommonAll<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO: Comparable<R>
			L extends ISharedLogicalClauses<MODEL, RESULT>> 

			extends ISharedClauseComparableCommonValue<MODEL, RESULT, R, L>,
					ISharedClauseComparableCommonParam<MODEL, RESULT, R, L> {

}

