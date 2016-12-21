package com.neaterbits.query.sql.dsl.api;

public interface ISharedClauseComparativeStringParam<MODEL, RESULT, L extends ISharedLogicalClauses<MODEL, RESULT>> {

	L startsWith(Param<String> s);
    
    L endsWith(Param<String> s);
    
    L contains(Param<String> s);
    
    L matches(Param<String> regex);
}
