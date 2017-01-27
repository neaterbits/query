package com.neaterbits.query.sql.dsl.api;


public interface ISharedCondition_Comparable_Common_All<
			MODEL,
			RESULT,
			R extends Comparable<?>, // TODO: Comparable<R>
			L extends ISharedLogical_Base<MODEL, RESULT>> 

			extends ISharedCondition_Comparable_Common_Value<MODEL, RESULT, R, L>,
					ISharedCondition_Comparable_Common_Param<MODEL, RESULT, R, L> {

}

