package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface OrClausesTableSingle<MODEL, RESULT> extends LogicalClauses<MODEL, RESULT> {

    <RR> ConditionClause<MODEL, RESULT, RR, OrClausesTableSingle<MODEL, RESULT>> or(Function<RESULT, RR> getter);
    
    StringClause<MODEL, RESULT, OrClausesTableSingle<MODEL, RESULT>> or(StringFunction<RESULT> getter);
	
}
