package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedResultMapper_Named<MODEL, RESULT, SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> {

	//<T, R> ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> map(Function<T, R> getter);
	
	// <T, R> ISharedResultMapperTo<MODEL, RESULT, R, SOURCE> map(Function<T, R> getter);
	
	/*
	<T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Short, SOURCE>      map(IFunctionShort<T> getter);
	<T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Integer, SOURCE>    map(IFunctionInteger<T> getter);
	<T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, SOURCE>	      map(IFunctionLong<T> getter);
	<T> ISharedResultOps_Numeric_Named<MODEL, RESULT, BigDecimal, SOURCE> map(IFunctionBigDecimal<T> getter);
	*/

	<T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Short, SOURCE>      map(IFunctionShort<T> getter);
	<T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Integer, SOURCE>    map(IFunctionInteger<T> getter);
	<T> ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, SOURCE>	      map(IFunctionLong<T> getter);
	<T> ISharedResultOps_Numeric_Named<MODEL, RESULT, BigDecimal, SOURCE> map(IFunctionBigDecimal<T> getter);


	// String maps to "to" without arithmetic ops (though concat could have been plus-variant like in Java instead of a nested-call)
	<T> ISharedResultOps_String_Named<MODEL, RESULT, SOURCE> map(StringFunction<T> getter);


	// TODO ISharedResultOps_Numeric_Named<MODEL, RESULT, BigDecimal, SOURCE> mapOf(ISharedSubOperandsFunction_Named<MODEL, RESULT, BigDecimal> sub);
	
	//<T> ISharedResultMapperTo<MODEL, RESULT, Integer, SOURCE> map(IFunctionInteger<T> getter);
}
