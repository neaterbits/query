package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface IClassicSingleWhereClauseBuilderTable<MODEL, RESULT> extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<R> ISharedClauseConditionAll<MODEL, RESULT, R, IClassicSingleAndOrLogicalClausesTable<MODEL, RESULT>> where(Function<RESULT, R> func);
	
    ISharedClauseComparativeStringAll<MODEL, RESULT, IClassicSingleAndOrLogicalClausesTable<MODEL, RESULT>> where(StringFunction<RESULT> func);
	
}
