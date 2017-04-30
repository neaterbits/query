package com.neaterbits.query.sql.dsl.api;

public interface ISharedComparison_Comparable_Common_Base<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO : Comparable<R>
			L extends ISharedLogical_Base<MODEL, RESULT>>

		extends ISharedComparison_Base<MODEL, RESULT, R, L> {

}
