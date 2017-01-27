package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_Comparable_Common_Base<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO : Comparable<R>
			L extends ISharedLogical_Base<MODEL, RESULT>>

		extends ISharedCondition_Base<MODEL, RESULT, R, L> {

}
