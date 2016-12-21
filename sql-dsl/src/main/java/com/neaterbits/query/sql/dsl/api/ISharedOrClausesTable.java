package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES>> extends ISharedOrClauses<MODEL, RESULT> {

    <T> ISharedClauseConditionAll<MODEL, RESULT, Integer, OR_CLAUSES> or(IFunctionInteger<T> getter);
    
    <T> ISharedClauseConditionAll<MODEL, RESULT, Long, OR_CLAUSES> or(IFunctionLong<T> getter);

    <T> ISharedClauseComparableStringAll<MODEL, RESULT, OR_CLAUSES> or(StringFunction<T> getter);
}
