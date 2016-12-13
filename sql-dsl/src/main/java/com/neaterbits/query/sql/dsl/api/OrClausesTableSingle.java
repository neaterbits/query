package com.neaterbits.query.sql.dsl.api;


public interface OrClausesTableSingle<MODEL, RESULT> extends LogicalClauses<MODEL, RESULT> {

    ConditionClause<MODEL, RESULT, Integer, OrClausesTableSingle<MODEL, RESULT>> or(IntegerFunction<RESULT> getter);
    
    StringClause<MODEL, RESULT, OrClausesTableSingle<MODEL, RESULT>> or(StringFunction<RESULT> getter);
	
}
