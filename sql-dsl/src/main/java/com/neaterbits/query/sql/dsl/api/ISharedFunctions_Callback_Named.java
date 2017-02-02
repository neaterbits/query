package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedFunctions_Callback_Named<MODEL, RESULT, RET extends ISharedLogical_Base<MODEL, RESULT>, COLLECTED> {
	ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, RET> onComparable(COLLECTED functions, Function<?, ? extends Comparable<?>> getter);
	
	ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET> onString(COLLECTED functions, StringFunction<?> getter);

}
