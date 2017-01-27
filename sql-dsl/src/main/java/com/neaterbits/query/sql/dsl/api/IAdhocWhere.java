package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface IAdhocWhere<MODEL, RESULT, T, AND_OR_CLAUSES extends IAdhocLogical_And_Or<MODEL,RESULT, T>
		
		> {

	<E extends Enum<E>> ISharedCondition_Equality_Value<MODEL, RESULT, E, AND_OR_CLAUSES> where(IFunctionEnum<T, E> func);

	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, AND_OR_CLAUSES> where(IFunctionInteger<T> func);

	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, AND_OR_CLAUSES> where(IFunctionBigDecimal<T> func);

	ISharedCondition_Comparable_String_Value<MODEL, RESULT, AND_OR_CLAUSES> where(StringFunction<T> func);
	
}
