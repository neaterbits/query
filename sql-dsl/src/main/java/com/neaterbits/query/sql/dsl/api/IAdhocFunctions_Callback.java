package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

interface IAdhocFunctions_Callback<MODEL, RESULT, RET extends ISharedLogical_Base<MODEL,RESULT>>

//		extends ISharedFunctions_Callback_Named<MODEL, RESULT, RET, AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?, ?>> {

{
	ISharedFunction_Next<MODEL, RESULT, RET> onComparable(AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?, ?> functions, Function<?, ? extends Comparable<?>> getter);

	ISharedFunction_Next<MODEL, RESULT, RET> onString(AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?, ?> functions, IFunctionString<?> getter);
	

}
