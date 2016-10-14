package com.neaterbits.gui.sql.dsl.api;

import java.util.function.Function;

public interface AndClauses<MODEL, RESULT>  extends LogicalClauses<MODEL, RESULT> {

    <T, RR> ConditionClause<MODEL, RESULT, RR, AndClauses<MODEL, RESULT>> and(Function<T, RR> getter);

    <T> StringClause<MODEL, RESULT, AndClauses<MODEL, RESULT>> and(StringFunction<T> getter);

}
