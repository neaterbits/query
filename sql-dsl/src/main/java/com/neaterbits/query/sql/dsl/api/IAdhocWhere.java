package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface IAdhocWhere<MODEL, RESULT, T> {

	<E extends Enum<E>> ISharedClauseConditionValue<MODEL, Object, E, IAdhocAndOrLogicalClauses<MODEL, Object>> where(IFunctionEnum<T, E> func);
	
	ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocAndOrLogicalClauses<MODEL,RESULT>> where(IFunctionInteger<T> func);

	ISharedClauseComparableCommonValue<MODEL, RESULT, BigDecimal, IAdhocAndOrLogicalClauses<MODEL,RESULT>> where(IFunctionBigDecimal<T> func);

	ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocAndOrLogicalClauses<MODEL,RESULT>> where(StringFunction<T> func);
	
}
