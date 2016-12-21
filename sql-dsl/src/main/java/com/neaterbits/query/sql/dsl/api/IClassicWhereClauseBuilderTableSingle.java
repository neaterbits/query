package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface IClassicWhereClauseBuilderTableSingle<MODEL, RESULT> extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<R> ISharedConditionClause<MODEL, RESULT, R, AndOrLogicalClausesTableSingle<MODEL, RESULT>> where(Function<RESULT, R> func);
	
    ISharedStringClause<MODEL, RESULT, AndOrLogicalClausesTableSingle<MODEL, RESULT>> where(StringFunction<RESULT> func);
	
}
