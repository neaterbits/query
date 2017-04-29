package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Undecided<
	MODEL,
	RESULT,
	
	NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
	ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
	
	NAMED_BYTE_RET  		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	NAMED_SHORT_RET  		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	NAMED_INTEGER_RET  		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	NAMED_LONG_RET 	 		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	NAMED_BIGINTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	NAMED_FLOAT_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	NAMED_DOUBLE_RET   		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	NAMED_BIGDECIMAL_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
	
	ALIAS_BYTE_RET  		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
	ALIAS_SHORT_RET  		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
	ALIAS_INTEGER_RET  		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
	ALIAS_LONG_RET 	 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
	ALIAS_BIGINTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
	ALIAS_FLOAT_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
	ALIAS_DOUBLE_RET   		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
	ALIAS_BIGDECIMAL_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
	> {


	<T> NAMED_BYTE_RET 		abs(IFunctionByte<T> getter);
	<T> NAMED_SHORT_RET 		abs(IFunctionShort<T> getter);
	<T> NAMED_INTEGER_RET 	abs(IFunctionInteger<T> getter);
	<T> NAMED_LONG_RET 		abs(IFunctionLong<T> getter);
	<T> NAMED_BIGINTEGER_RET 	abs(IFunctionBigInteger<T> getter);
	<T> NAMED_FLOAT_RET 		abs(IFunctionFloat<T> getter);
	<T> NAMED_DOUBLE_RET 		abs(IFunctionDouble<T> getter);
	<T> NAMED_BIGDECIMAL_RET 	abs(IFunctionBigDecimal<T> getter);
	
	
	<BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, ?>> BYTE_RET  	 absOfByte(ISharedSubOperandsFunction_Byte_Undecided<MODEL, RESULT> sub);
	<SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, ?>> SHORT_RET 	 absOfShort(ISharedSubOperandsFunction_Short_Undecided<MODEL, RESULT> sub);
	<INTEGER_RET	extends ISharedSubOperand_End_Undecided<MODEL, RESULT, Integer>> INTEGER_RET absOfInteger(ISharedSubOperandsFunction_Integer_Undecided<MODEL, RESULT, INTEGER_RET> sub);
	<LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, ?>> LONG_RET		 absOfLong(ISharedSubOperandsFunction_Long_Undecided<MODEL, RESULT> sub);
	<BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, ?>> BIGINTEGER_RET absOfBigInteger(ISharedSubOperandsFunction_BigInteger_Undecided<MODEL, RESULT> sub);
	<FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, ?>> FLOAT_RET		 absOfFloat(ISharedSubOperandsFunction_Float_Undecided<MODEL, RESULT> sub);
	<DOUBLE_RET		extends ISharedFunction_Next<MODEL, RESULT, ?>> DOUBLE_RET	 absOfDouble(ISharedSubOperandsFunction_Double_Undecided<MODEL, RESULT> sub);
	<BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, ?>> BIGDECIMAL_RET absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Undecided<MODEL, RESULT> sub);

	// sqrt always returns double
	<T> NAMED_DOUBLE_RET sqrt(IFunctionByte<T> getter);
	<T> NAMED_DOUBLE_RET sqrt(IFunctionShort<T> getter);
	<T> NAMED_DOUBLE_RET sqrt(IFunctionInteger<T> getter);
	<T> NAMED_DOUBLE_RET sqrt(IFunctionLong<T> getter);
	<T> NAMED_DOUBLE_RET sqrt(IFunctionBigInteger<T> getter);
	<T> NAMED_DOUBLE_RET sqrt(IFunctionFloat<T> getter);
	<T> NAMED_DOUBLE_RET sqrt(IFunctionDouble<T> getter);
	<T> NAMED_DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter);

	<DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ?>> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Byte_Undecided<MODEL, RESULT> sub);
	<DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ?>> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Short_Undecided<MODEL, RESULT> sub);
	<DOUBLE_RET	extends ISharedSubOperand_End_Undecided<MODEL, RESULT, Integer>> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Integer_Undecided<MODEL, RESULT, DOUBLE_RET> sub);
	<DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ?>> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_BigInteger_Undecided<MODEL, RESULT> sub);
	<DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ?>> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Long_Undecided<MODEL, RESULT> sub);
	<DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ?>> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Float_Undecided<MODEL, RESULT> sub);
	<DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ?>> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Undecided<MODEL, RESULT> sub);
	<DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ?>> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_BigDecimal_Undecided<MODEL, RESULT> sub);
	
	<T> NAMED_INTEGER_RET 	mod(IFunctionInteger<T> getter, int value);
	<INTEGER_RET extends ISharedSubOperand_End_Undecided<MODEL, RESULT, Integer>> INTEGER_RET modOf(ISharedSubOperandsFunction_Integer_Undecided<MODEL, RESULT, INTEGER_RET> sub, int value);
}
