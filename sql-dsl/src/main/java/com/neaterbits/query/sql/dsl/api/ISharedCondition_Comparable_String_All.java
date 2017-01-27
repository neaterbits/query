package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_Comparable_String_All<MODEL, RESULT, L extends ISharedLogical_Base<MODEL, RESULT>>
		extends ISharedCondition_Comparable_Common_All<MODEL, RESULT, String, L>,
				ISharedCondition_Comparable_String_Value<MODEL, RESULT, L>,
				ISharedCondition_Comparable_String_Param<MODEL, RESULT, L> {

}
