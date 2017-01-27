package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET extends ISharedLogical_Base<MODEL, RESULT>> {

	ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, RET> onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter);
	
	ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET> onString(CollectedFunctions functions, ISupplierString getter);
}