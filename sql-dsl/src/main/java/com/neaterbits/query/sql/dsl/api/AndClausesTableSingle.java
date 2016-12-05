package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface AndClausesTableSingle<MODEL, RESULT> extends LogicalClauses<MODEL, RESULT> {

    <RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTableSingle<MODEL, RESULT>> and(Function<RESULT, RR> getter);

    StringClause<MODEL, RESULT, AndClausesTableSingle<MODEL, RESULT>> and(StringFunction<RESULT> getter);
	
}
