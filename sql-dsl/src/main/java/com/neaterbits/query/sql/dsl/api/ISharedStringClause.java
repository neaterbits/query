package com.neaterbits.query.sql.dsl.api;

public interface ISharedStringClause<MODEL, RESULT, L extends ISharedLogicalClauses<MODEL, RESULT>>  extends ISharedComparativeClause<MODEL, RESULT, String, L> {

    L startsWith(String s);

    L startsWith(Param<String> s);
    
    //<T> L startsWith(Function<T, String> func);
    
    L endsWith(String s);

    L endsWith(Param<String> s);
    
    //<T> L endsWith(Function<T, String> func);

    L contains(String s);

    L contains(Param<String> s);
    
    //<T> L contains(Function<T, String> func);

    L matches(String regex);

    L matches(Param<String> regex);
}
