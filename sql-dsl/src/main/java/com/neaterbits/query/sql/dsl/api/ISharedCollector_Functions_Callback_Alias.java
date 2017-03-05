package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedCollector_Functions_Callback_Alias<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>
	> {

		ISharedFunction_Next<MODEL, RESULT, RET> onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter);
	
		ISharedFunction_Next<MODEL, RESULT, RET> onString(CollectedFunctions functions, ISupplierString getter);
}