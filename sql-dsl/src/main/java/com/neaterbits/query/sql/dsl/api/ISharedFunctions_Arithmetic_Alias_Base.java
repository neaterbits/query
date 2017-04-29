package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Alias_Base<

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
	
	
	// sqrt() always return double-type
	DOUBLE_RET sqrt(ISupplierByte getter);
	DOUBLE_RET sqrt(ISupplierShort getter);
	DOUBLE_RET sqrt(ISupplierInteger getter);
	DOUBLE_RET sqrt(ISupplierLong getter);
	DOUBLE_RET sqrt(ISupplierFloat getter);
	DOUBLE_RET sqrt(ISupplierBigInteger getter);
	DOUBLE_RET sqrt(ISupplierDouble getter);
	DOUBLE_RET sqrt(ISupplierBigDecimal getter);
	
	
	INTEGER_RET 	mod(ISupplierInteger getter, int value);
}
