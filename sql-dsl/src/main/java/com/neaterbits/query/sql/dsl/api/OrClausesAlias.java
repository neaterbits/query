package com.neaterbits.query.sql.dsl.api;


public interface OrClausesAlias<MODEL, RESULT> extends OrClauses<MODEL, RESULT> {
	
    ConditionClause<MODEL, RESULT, Integer, OrClausesAlias<MODEL, RESULT>> or(IntegerSupplier getter);

    ConditionClause<MODEL, RESULT, Long, OrClausesAlias<MODEL, RESULT>> or(LongSupplier getter);
    
    StringClause<MODEL, RESULT, OrClausesAlias<MODEL, RESULT>> or(StringSupplier getter);

}
