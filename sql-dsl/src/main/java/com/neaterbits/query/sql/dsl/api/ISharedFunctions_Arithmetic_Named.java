package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Named<
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

	
	BYTE_RET 		absOfByte(ISharedSubOperandsFunction_Byte_Named<MODEL, RESULT> sub);
	SHORT_RET 		absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub);
	INTEGER_RET 	absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub);
	LONG_RET 		absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub);
	BIGINTEGER_RET 	absOfBigInteger(ISharedSubOperandsFunction_BigInteger_Named<MODEL, RESULT> sub);
	FLOAT_RET 		absOfFloat(ISharedSubOperandsFunction_Float_Named<MODEL, RESULT> sub);
	DOUBLE_RET 		absOfDouble(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub);
	BIGDECIMAL_RET 	absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub);

	// sqrt always returns double
	<T> DOUBLE_RET sqrt(IFunctionByte<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionShort<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionInteger<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionLong<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionBigInteger<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionFloat<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionDouble<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter);

	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Byte_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_BigInteger_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Float_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub);
	
	<T> INTEGER_RET 	mod(IFunctionInteger<T> getter, int value);
	INTEGER_RET 	modOf(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub, int value);
}
