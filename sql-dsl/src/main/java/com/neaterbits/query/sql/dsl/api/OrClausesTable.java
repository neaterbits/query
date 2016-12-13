package com.neaterbits.query.sql.dsl.api;

public interface OrClausesTable<MODEL, RESULT> extends OrClauses<MODEL, RESULT> {

    <T> ConditionClause<MODEL, RESULT, Integer, OrClausesTable<MODEL, RESULT>> or(IntegerFunction<T> getter);
    
    <T> ConditionClause<MODEL, RESULT, Long, OrClausesTable<MODEL, RESULT>> or(LongFunction<T> getter);

    <T> StringClause<MODEL, RESULT, OrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter);
}
