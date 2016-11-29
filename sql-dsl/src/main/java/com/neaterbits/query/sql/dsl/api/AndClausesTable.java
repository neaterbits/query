package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface AndClausesTable<MODEL, RESULT> extends AndClauses<MODEL, RESULT> {
	
    <T, RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTable<MODEL, RESULT>> and(Function<T, RR> getter);

    <T> StringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter);

}
