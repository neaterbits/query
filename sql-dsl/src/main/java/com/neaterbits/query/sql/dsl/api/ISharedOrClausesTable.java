package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES>> extends ISharedOrClauses<MODEL, RESULT> {

    <T> ISharedConditionClause<MODEL, RESULT, Integer, OR_CLAUSES> or(IntegerFunction<T> getter);
    
    <T> ISharedConditionClause<MODEL, RESULT, Long, OR_CLAUSES> or(LongFunction<T> getter);

    <T> ISharedStringClause<MODEL, RESULT, OR_CLAUSES> or(StringFunction<T> getter);
}
