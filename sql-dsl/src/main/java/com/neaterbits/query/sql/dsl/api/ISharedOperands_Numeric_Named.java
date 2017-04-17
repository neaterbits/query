package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedOperands_Numeric_Named<
	MODEL,
	RESULT,
	
	RET extends ISharedFunction_After<MODEL, RESULT>,

	TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>

	> extends ISharedOperands_Numeric_Common<MODEL, RESULT, RET, TYPE_RET> {
	
	/* We need types for all to make sure one can add integer-field and short-field.
	 * But we only need one return-type (for now)
	 * 
	 *  
	 * 
	<T> TYPE_RET plus(Function<T, R> getter);
	<T> TYPE_RET plus(ISharedSubOperandsBuilder<R> builder);
	<T> TYPE_RET plus(R value);
	<T> TYPE_RET plus(Param<R> param);
	*/

	<T> TYPE_RET plus(IFunctionShort<T> getter);
	//<T> TYPE_RET plus(ISharedSubOperandsBuilder<Short> builder);
	//<T> TYPE_RET plus(Param<Short> param);

	<T> TYPE_RET plus(IFunctionBigDecimal<T> getter);
	
	TYPE_RET plusOf(ISharedSubOperandsFunction_Named<MODEL, RESULT, BigDecimal> builder);
	
	
	//<T> TYPE_RET plus(Param<Integer> param);
	
}
