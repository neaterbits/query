package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface StringClause<MODEL, RESULT, L extends LogicalClauses<MODEL, RESULT>>  extends ComparativeClause<MODEL, RESULT, String, L> {

    L startsWith(String s);
    
    <T> L startsWith(Function<T, String> func);
    
    L endsWith(String s);
    
    <T> L endsWith(Function<T, String> func);

    L contains(String s);
    
    <T> L contains(Function<T, String> func);

    L matches(String regex);
}
