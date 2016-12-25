package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface IAdhocWhere<MODEL, RESULT, T, AND_OR_CLAUSES extends IAdhocAndOrLogicalClauses<MODEL,RESULT>
		
		> {

	<E extends Enum<E>> ISharedClauseConditionValue<MODEL, RESULT, E, AND_OR_CLAUSES> where(IFunctionEnum<T, E> func);

	ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, AND_OR_CLAUSES> where(IFunctionInteger<T> func);

	ISharedClauseComparableCommonValue<MODEL, RESULT, BigDecimal, AND_OR_CLAUSES> where(IFunctionBigDecimal<T> func);

	ISharedClauseComparableStringValue<MODEL, RESULT, AND_OR_CLAUSES> where(StringFunction<T> func);
	
}
