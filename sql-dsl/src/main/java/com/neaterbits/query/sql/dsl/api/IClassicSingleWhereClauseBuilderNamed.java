package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface IClassicSingleWhereClauseBuilderNamed<MODEL, RESULT> extends ISharedWhereClauseBuilder<MODEL, RESULT> {

	<R> ISharedClauseConditionAll<MODEL, RESULT, R, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>> where(Function<RESULT, R> func);
	
    ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>> where(StringFunction<RESULT> func);
	
}
