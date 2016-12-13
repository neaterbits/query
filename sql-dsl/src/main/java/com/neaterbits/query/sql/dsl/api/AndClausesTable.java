package com.neaterbits.query.sql.dsl.api;


public interface AndClausesTable<MODEL, RESULT> extends AndClauses<MODEL, RESULT> {
	
    <T> ConditionClauseTable<MODEL, RESULT, Integer, AndClausesTable<MODEL, RESULT>> and(IntegerFunction<T> getter);

    <T> ConditionClauseTable<MODEL, RESULT, Long, AndClausesTable<MODEL, RESULT>> and(LongFunction<T> getter);

    <T> StringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter);

}
