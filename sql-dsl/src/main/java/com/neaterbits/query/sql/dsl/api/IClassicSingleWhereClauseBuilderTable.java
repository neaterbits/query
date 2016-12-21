package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface IClassicSingleWhereClauseBuilderTable<MODEL, RESULT> extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<R> ISharedConditionClause<MODEL, RESULT, R, IClassicSingleAndOrLogicalClausesTable<MODEL, RESULT>> where(Function<RESULT, R> func);
	
    ISharedStringClause<MODEL, RESULT, IClassicSingleAndOrLogicalClausesTable<MODEL, RESULT>> where(StringFunction<RESULT> func);
	
}
