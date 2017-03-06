package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Alias<

		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		//SHORT_CLAUSE 	  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET 	  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET    	  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET    	  extends ISharedFunction_Next<MODEL, RESULT, RET>
		//BIGDECIMAL_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		> {

	//<T> SHORT_CLAUSE abs(ISupplierShort getter);
	<T> INTEGER_RET abs(ISupplierInteger getter);
	<T> LONG_RET abs(ISupplierLong getter);
	//<T> BIGDECIMAL_CLAUSE abs(ISupplierBigDecimal getter);


	// sqrt() always return double-type
	<T> DOUBLE_RET sqrt(ISupplierInteger getter);
	<T> DOUBLE_RET sqrt(ISupplierLong getter);
	
}
