package com.neaterbits.query.sql.dsl.api;

public interface ISharedComparison_Comparable_String_All<MODEL, RESULT, L extends ISharedLogical_Base<MODEL, RESULT>>
		extends ISharedComparison_Comparable_Common_All<MODEL, RESULT, String, L>,
				ISharedComparison_Comparable_String_Value<MODEL, RESULT, L>,
				ISharedComparison_Comparable_String_Param<MODEL, RESULT, L> {

}
