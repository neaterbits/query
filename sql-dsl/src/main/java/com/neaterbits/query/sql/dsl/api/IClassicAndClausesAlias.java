package com.neaterbits.query.sql.dsl.api;


public interface IClassicAndClausesAlias<MODEL, RESULT> extends ISharedAndClauses<MODEL, RESULT> {

	ISharedConditionClauseAlias<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>> and(IntegerSupplier getter);

	ISharedConditionClauseAlias<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>> and(LongSupplier getter);

    ISharedStringClause<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> and(StringSupplier getter);

}
