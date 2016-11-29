package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface OrClausesAlias<MODEL, RESULT> extends OrClauses<MODEL, RESULT> {
	
    <RR> ConditionClause<MODEL, RESULT, RR, OrClausesAlias<MODEL, RESULT>> or(Supplier<RR> getter);
    
    StringClause<MODEL, RESULT, OrClausesAlias<MODEL, RESULT>> or(StringSupplier getter);

}
