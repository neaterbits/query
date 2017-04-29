package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Named_Base<
	MODEL,
	RESULT,
	
	RET extends ISharedFunction_After<MODEL, RESULT>,
	
	BYTE_RET  		extends ISharedFunction_Next<MODEL, RESULT, RET>,
	SHORT_RET  		extends ISharedFunction_Next<MODEL, RESULT, RET>,
	INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	BIGINTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	FLOAT_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>

> {

	
	<T> BYTE_RET 		abs(IFunctionByte<T> getter);
	<T> SHORT_RET 		abs(IFunctionShort<T> getter);
	<T> INTEGER_RET 	abs(IFunctionInteger<T> getter);
	<T> LONG_RET 		abs(IFunctionLong<T> getter);
	<T> BIGINTEGER_RET 	abs(IFunctionBigInteger<T> getter);
	<T> FLOAT_RET 		abs(IFunctionFloat<T> getter);
	<T> DOUBLE_RET 		abs(IFunctionDouble<T> getter);
	<T> BIGDECIMAL_RET 	abs(IFunctionBigDecimal<T> getter);
	
	// sqrt always returns double
	<T> DOUBLE_RET sqrt(IFunctionByte<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionShort<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionInteger<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionLong<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionBigInteger<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionFloat<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionDouble<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter);
	
	<T> INTEGER_RET 	mod(IFunctionInteger<T> getter, int value);
}
