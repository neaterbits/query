package com.neaterbits.query.sql.dsl.api;


public interface AndClausesTableSingle<MODEL, RESULT> extends ISharedLogicalClauses<MODEL, RESULT> {

    ISharedConditionClauseTable<MODEL, RESULT, Integer, AndClausesTableSingle<MODEL, RESULT>> and(IntegerFunction<RESULT> getter);

    ISharedStringClause<MODEL, RESULT, AndClausesTableSingle<MODEL, RESULT>> and(StringFunction<RESULT> getter);
	
}
