package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedFunctions_Callback_Named<MODEL, RESULT, RET extends ISharedFunction_After<MODEL, RESULT>, COLLECTED> {

	ISharedFunction_Next<MODEL, RESULT, RET> onComparable(COLLECTED functions, Function<?, ? extends Comparable<?>> getter);

	ISharedFunction_Next<MODEL, RESULT, RET> onString(COLLECTED functions, StringFunction<?> getter);
}

