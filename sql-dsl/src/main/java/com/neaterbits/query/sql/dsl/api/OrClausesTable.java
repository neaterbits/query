package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface OrClausesTable<MODEL, RESULT> extends OrClauses<MODEL, RESULT> {

    <T, RR> ConditionClause<MODEL, RESULT, RR, OrClausesTable<MODEL, RESULT>> or(Function<T, RR> getter);
    
    <T> StringClause<MODEL, RESULT, OrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter);
}
