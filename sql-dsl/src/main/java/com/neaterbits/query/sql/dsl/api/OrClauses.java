package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface OrClauses<MODEL, RESULT> extends LogicalClauses<MODEL, RESULT> {

    <T, RR> ConditionClause<MODEL, RESULT, RR, OrClauses<MODEL, RESULT>> or(Function<T, RR> getter);
    
    <T> StringClause<MODEL, RESULT, OrClauses<MODEL, RESULT>> or(StringFunction<T> getter);

}
