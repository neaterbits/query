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

	<T> BYTE_RET 	abs(ISupplierByte getter);
	<T> SHORT_RET 	abs(ISupplierShort getter);
	<T> INTEGER_RET abs(ISupplierInteger getter);
	<T> LONG_RET 	abs(ISupplierLong getter);
	<T> BIGINTEGER_RET abs(ISupplierBigInteger getter);
	<T> FLOAT_RET 	abs(ISupplierFloat getter);
	<T> DOUBLE_RET 	abs(ISupplierDouble getter);
	<T> BIGDECIMAL_RET abs(ISupplierBigDecimal getter);

	<T> BYTE_RET 		absOfByte(ISharedSubOperandsFunction_Byte_Alias<MODEL, RESULT> sub);
	<T> SHORT_RET 		absOfShort(ISharedSubOperandsFunction_Short_Alias<MODEL, RESULT> sub);
	<T> INTEGER_RET 	absOfInteger(ISharedSubOperandsFunction_Integer_Alias<MODEL, RESULT> sub);
	<T> LONG_RET 		absOfLong(ISharedSubOperandsFunction_Long_Alias<MODEL, RESULT> sub);
	<T> BIGINTEGER_RET 	absOfBigInteger(ISharedSubOperandsFunction_BigInteger_Alias<MODEL, RESULT> sub);
	<T> FLOAT_RET 		absOfFloat(ISharedSubOperandsFunction_Float_Alias<MODEL, RESULT> sub);
	<T> DOUBLE_RET 		absOfDouble(ISharedSubOperandsFunction_Double_Alias<MODEL, RESULT> sub);
	<T> BIGDECIMAL_RET 	absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Alias<MODEL, RESULT> sub);

	
	// sqrt() always return double-type
	<T> DOUBLE_RET sqrt(ISupplierByte getter);
	<T> DOUBLE_RET sqrt(ISupplierShort getter);
	<T> DOUBLE_RET sqrt(ISupplierInteger getter);
	<T> DOUBLE_RET sqrt(ISupplierLong getter);
	<T> DOUBLE_RET sqrt(ISupplierFloat getter);
	<T> DOUBLE_RET sqrt(ISupplierBigInteger getter);
	<T> DOUBLE_RET sqrt(ISupplierDouble getter);
	<T> DOUBLE_RET sqrt(ISupplierBigDecimal getter);

	<T> INTEGER_RET 	mod(ISupplierInteger getter, int value);
	<T> INTEGER_RET 	modOf(ISharedSubOperandsFunction_Integer_Alias<MODEL, RESULT> sub, int value);
}
