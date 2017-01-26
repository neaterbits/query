package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparableStringParam<MODEL, RESULT, L extends ISharedLogicalClauses<MODEL, RESULT>> {

	L startsWith(ValParam<String> s);
    
    L endsWith(ValParam<String> s);
    
    L contains(ValParam<String> s);
    
    L matches(ValParam<String> regex);
}
