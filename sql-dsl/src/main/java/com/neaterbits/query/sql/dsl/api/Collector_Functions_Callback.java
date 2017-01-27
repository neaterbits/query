package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

interface Collector_Functions_Callback<MODEL, RESULT, RET extends ISharedLogicalClauses<MODEL, RESULT>> {

	ISharedClauseComparableCommonBase<MODEL, RESULT, Comparable<?>, RET> onComparable(CollectedFunctions functions, Function<?, ? extends Comparable<?>> getter);
	
	ISharedClauseComparableStringBase<MODEL, RESULT, RET> onString(CollectedFunctions functions, StringFunction<?> getter);
}
