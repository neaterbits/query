package com.neaterbits.query.sql.dsl.api;

public interface ISharedComparison_Comparable_String_Param<MODEL, RESULT, L extends ISharedLogical_Base<MODEL, RESULT>> {

	L startsWith(ValParam<String> s);
    
    L endsWith(ValParam<String> s);
    
    L contains(ValParam<String> s);
    
    L matches(ValParam<String> regex);
}
