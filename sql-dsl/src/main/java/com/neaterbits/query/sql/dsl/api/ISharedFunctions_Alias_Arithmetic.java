package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_Arithmetic<
		MODEL,
		RESULT,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, RET>> {

	<T> COMPARABLE_CLAUSE abs(ISupplierInteger getter);
	<T> COMPARABLE_CLAUSE abs(ISupplierLong getter);
	ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> abs();


	<T> COMPARABLE_CLAUSE sqrt(ISupplierInteger getter);
	<T> COMPARABLE_CLAUSE sqrt(ISupplierLong getter);
	
	ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> sqrt();
	
}
