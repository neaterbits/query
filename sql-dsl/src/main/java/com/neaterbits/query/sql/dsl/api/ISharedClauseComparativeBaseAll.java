package com.neaterbits.query.sql.dsl.api;


public interface ISharedClauseComparativeBaseAll<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO: Comparable<R>
			L extends ISharedLogicalClauses<MODEL, RESULT>> 

			extends ISharedClauseComparativeBaseValue<MODEL, RESULT, R, L>,
					ISharedClauseComparativeBaseParam<MODEL, RESULT, R, L> {

}

