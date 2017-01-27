package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

interface Collector_Functions_Callback<MODEL, RESULT, RET extends ISharedLogical_Base<MODEL, RESULT>> {

	ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, RET> onComparable(CollectedFunctions functions, Function<?, ? extends Comparable<?>> getter);
	
	ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET> onString(CollectedFunctions functions, StringFunction<?> getter);
}
