package com.neaterbits.query.sql.dsl.api;


public interface AndClausesAlias<MODEL, RESULT> extends AndClauses<MODEL, RESULT> {

	ConditionClauseAlias<MODEL, RESULT, Integer, AndClausesAlias<MODEL, RESULT>> and(IntegerSupplier getter);

	ConditionClauseAlias<MODEL, RESULT, Long, AndClausesAlias<MODEL, RESULT>> and(LongSupplier getter);

    StringClause<MODEL, RESULT, AndClausesAlias<MODEL, RESULT>> and(StringSupplier getter);

}
