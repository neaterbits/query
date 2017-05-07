package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Named_All<
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

	> 

	extends ISharedFunctions_Arithmetic_Named_Base<
		MODEL,
		RESULT,
		
		RET,
		
		BYTE_RET,
		SHORT_RET,
		INTEGER_RET,
		LONG_RET,
		BIGINTEGER_RET,
		FLOAT_RET,
		DOUBLE_RET,
		BIGDECIMAL_RET
		>	{

	BYTE_RET 		absOfByte(ISharedSubOperandsFunction_Byte_Named<MODEL, RESULT> sub);
	SHORT_RET 		absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub);
	INTEGER_RET 	absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub);
	LONG_RET 		absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub);
	BIGINTEGER_RET 	absOfBigInteger(ISharedSubOperandsFunction_BigInteger_Named<MODEL, RESULT> sub);
	FLOAT_RET 		absOfFloat(ISharedSubOperandsFunction_Float_Named<MODEL, RESULT> sub);
	DOUBLE_RET 		absOfDouble(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub);
	BIGDECIMAL_RET 	absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub);
	
	// sqrt always returns double
	
	DOUBLE_RET 	sqrtOfByte(ISharedSubOperandsFunction_Byte_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOfBigInteger(ISharedSubOperandsFunction_BigInteger_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOfFloat(ISharedSubOperandsFunction_Float_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOfDouble(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub);
	
	INTEGER_RET 	modOf(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub, int value);
}
