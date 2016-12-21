package com.neaterbits.query.sql.dsl.api;


public interface AndClausesTable<MODEL, RESULT> extends ISharedAndClauses<MODEL, RESULT> {
	
    <T> ISharedConditionClauseTable<MODEL, RESULT, Integer, AndClausesTable<MODEL, RESULT>> and(IntegerFunction<T> getter);

    <T> ISharedConditionClauseTable<MODEL, RESULT, Long, AndClausesTable<MODEL, RESULT>> and(LongFunction<T> getter);

    <T> ISharedStringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter);

}
