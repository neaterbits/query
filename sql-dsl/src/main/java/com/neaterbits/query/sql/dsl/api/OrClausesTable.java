package com.neaterbits.query.sql.dsl.api;

public interface OrClausesTable<MODEL, RESULT> extends ISharedOrClauses<MODEL, RESULT> {

    <T> ISharedConditionClause<MODEL, RESULT, Integer, OrClausesTable<MODEL, RESULT>> or(IntegerFunction<T> getter);
    
    <T> ISharedConditionClause<MODEL, RESULT, Long, OrClausesTable<MODEL, RESULT>> or(LongFunction<T> getter);

    <T> ISharedStringClause<MODEL, RESULT, OrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter);
}
