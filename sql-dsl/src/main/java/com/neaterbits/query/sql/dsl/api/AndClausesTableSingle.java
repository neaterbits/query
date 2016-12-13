package com.neaterbits.query.sql.dsl.api;


public interface AndClausesTableSingle<MODEL, RESULT> extends LogicalClauses<MODEL, RESULT> {

    ConditionClauseTable<MODEL, RESULT, Integer, AndClausesTableSingle<MODEL, RESULT>> and(IntegerFunction<RESULT> getter);

    StringClause<MODEL, RESULT, AndClausesTableSingle<MODEL, RESULT>> and(StringFunction<RESULT> getter);
	
}
