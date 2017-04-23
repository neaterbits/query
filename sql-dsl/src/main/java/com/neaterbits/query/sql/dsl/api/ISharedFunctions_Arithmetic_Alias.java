package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Alias<

		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		BYTE_RET 	   	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SHORT_RET 	   	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET    	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET       	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGINTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		FLOAT_RET       extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET     	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		> {

	BYTE_RET 	abs(ISupplierByte getter);
	SHORT_RET 	abs(ISupplierShort getter);
	INTEGER_RET abs(ISupplierInteger getter);
	LONG_RET 	abs(ISupplierLong getter);
	BIGINTEGER_RET abs(ISupplierBigInteger getter);
	FLOAT_RET 	abs(ISupplierFloat getter);
	DOUBLE_RET 	abs(ISupplierDouble getter);
	BIGDECIMAL_RET abs(ISupplierBigDecimal getter);

	BYTE_RET 		absOfByte(ISharedSubOperandsFunction_Byte_Alias<MODEL, RESULT> sub);
	SHORT_RET 		absOfShort(ISharedSubOperandsFunction_Short_Alias<MODEL, RESULT> sub);
	INTEGER_RET 	absOfInteger(ISharedSubOperandsFunction_Integer_Alias<MODEL, RESULT> sub);
	LONG_RET 		absOfLong(ISharedSubOperandsFunction_Long_Alias<MODEL, RESULT> sub);
	BIGINTEGER_RET 	absOfBigInteger(ISharedSubOperandsFunction_BigInteger_Alias<MODEL, RESULT> sub);
	FLOAT_RET 		absOfFloat(ISharedSubOperandsFunction_Float_Alias<MODEL, RESULT> sub);
	DOUBLE_RET 		absOfDouble(ISharedSubOperandsFunction_Double_Alias<MODEL, RESULT> sub);
	BIGDECIMAL_RET 	absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Alias<MODEL, RESULT> sub);

	
	// sqrt() always return double-type
	DOUBLE_RET sqrt(ISupplierByte getter);
	DOUBLE_RET sqrt(ISupplierShort getter);
	DOUBLE_RET sqrt(ISupplierInteger getter);
	DOUBLE_RET sqrt(ISupplierLong getter);
	DOUBLE_RET sqrt(ISupplierFloat getter);
	DOUBLE_RET sqrt(ISupplierBigInteger getter);
	DOUBLE_RET sqrt(ISupplierDouble getter);
	DOUBLE_RET sqrt(ISupplierBigDecimal getter);

	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Byte_Alias<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Short_Alias<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Integer_Alias<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_BigInteger_Alias<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Long_Alias<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Float_Alias<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Double_Alias<MODEL, RESULT> sub);
	DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_BigDecimal_Alias<MODEL, RESULT> sub);
	
	INTEGER_RET 	mod(ISupplierInteger getter, int value);
	INTEGER_RET 	modOf(ISharedSubOperandsFunction_Integer_Alias<MODEL, RESULT> sub, int value);
}
