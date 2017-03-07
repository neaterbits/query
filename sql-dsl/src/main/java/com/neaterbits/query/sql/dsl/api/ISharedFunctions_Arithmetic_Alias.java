package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Alias<

		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		SHORT_RET 	   extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET    extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET       extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET     extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		> {

	<T> SHORT_RET abs(ISupplierShort getter);
	<T> INTEGER_RET abs(ISupplierInteger getter);
	<T> LONG_RET abs(ISupplierLong getter);
	<T> DOUBLE_RET abs(ISupplierDouble getter);
	<T> BIGDECIMAL_RET abs(ISupplierBigDecimal getter);


	// sqrt() always return double-type
	<T> DOUBLE_RET sqrt(ISupplierShort getter);
	<T> DOUBLE_RET sqrt(ISupplierInteger getter);
	<T> DOUBLE_RET sqrt(ISupplierLong getter);
	<T> DOUBLE_RET sqrt(ISupplierDouble getter);
	<T> DOUBLE_RET sqrt(ISupplierBigDecimal getter);
}
