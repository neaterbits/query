package com.neaterbits.query.sql.dsl.api;


public interface OrClausesAlias<MODEL, RESULT> extends ISharedOrClauses<MODEL, RESULT> {
	
    ISharedConditionClause<MODEL, RESULT, Integer, OrClausesAlias<MODEL, RESULT>> or(IntegerSupplier getter);

    ISharedConditionClause<MODEL, RESULT, Long, OrClausesAlias<MODEL, RESULT>> or(LongSupplier getter);
    
    ISharedStringClause<MODEL, RESULT, OrClausesAlias<MODEL, RESULT>> or(StringSupplier getter);

}
