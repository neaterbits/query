package com.neaterbits.query.sql.dsl.api;


public interface ISharedAndClausesTable<MODEL, RESULT, AND_CLAUSES extends ISharedAndClausesTable<MODEL, RESULT, AND_CLAUSES>> extends ISharedAndClauses<MODEL, RESULT> {
	
    <T> ISharedClauseConditionTable<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<T> getter);

    <T> ISharedClauseConditionTable<MODEL, RESULT, Long, AND_CLAUSES> and(IFunctionLong<T> getter);

    <T> ISharedClauseComparativeStringAll<MODEL, RESULT, AND_CLAUSES> and(StringFunction<T> getter);

}
