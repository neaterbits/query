package com.neaterbits.query.sql.dsl.api;

public interface ISharedComparison_Comparable_String_Value<MODEL, RESULT, L extends ISharedLogical_Base<MODEL, RESULT>> 
	extends ISharedComparison_Comparable_String_Base<MODEL, RESULT, L> {

	L startsWith(String s);

    L endsWith(String s);

    L contains(String s);

    L matches(String regex);

}
