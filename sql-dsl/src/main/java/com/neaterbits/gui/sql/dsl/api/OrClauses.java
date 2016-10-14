package com.neaterbits.gui.sql.dsl.api;

import java.util.function.Function;

public interface OrClauses<MODEL, RESULT> extends LogicalClauses<MODEL, RESULT> {

    <R> ConditionClause<MODEL, RESULT, R, OrClauses<MODEL, RESULT>> or(Function<RESULT, R> getter);
    
    StringClause<MODEL, RESULT, OrClauses<MODEL, RESULT>> or(StringFunction<RESULT> getter);

}
