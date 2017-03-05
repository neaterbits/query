package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_Arithmetic<

		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		INTEGER_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_CLAUSE    extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		> {

	<T> INTEGER_CLAUSE abs(ISupplierInteger getter);
	<T> LONG_CLAUSE abs(ISupplierLong getter);


	<T> INTEGER_CLAUSE sqrt(ISupplierInteger getter);
	<T> LONG_CLAUSE sqrt(ISupplierLong getter);
	
}
