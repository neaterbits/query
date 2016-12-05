package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface WhereClauseBuilderTableSingle<MODEL, RESULT> extends WhereClauseBuilder<MODEL, RESULT> {

	<R> ConditionClause<MODEL, RESULT, R, AndOrLogicalClausesTableSingle<MODEL, RESULT>> where(Function<RESULT, R> func);
	
    StringClause<MODEL, RESULT, AndOrLogicalClausesTableSingle<MODEL, RESULT>> where(StringFunction<RESULT> func);
	
}
