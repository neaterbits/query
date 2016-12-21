package com.neaterbits.query.sql.dsl.api;


public interface OrClausesTableSingle<MODEL, RESULT> extends ISharedLogicalClauses<MODEL, RESULT> {

    ISharedConditionClause<MODEL, RESULT, Integer, OrClausesTableSingle<MODEL, RESULT>> or(IntegerFunction<RESULT> getter);
    
    ISharedStringClause<MODEL, RESULT, OrClausesTableSingle<MODEL, RESULT>> or(StringFunction<RESULT> getter);
	
}
