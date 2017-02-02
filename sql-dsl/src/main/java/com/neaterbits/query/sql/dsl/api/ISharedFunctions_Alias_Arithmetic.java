package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_Arithmetic<
		MODEL,
		RESULT,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>> {

	<T> INTEGER_CLAUSE abs(ISupplierInteger getter);
	<T> LONG_CLAUSE abs(ISupplierLong getter);
	ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs();


	<T> INTEGER_CLAUSE sqrt(ISupplierInteger getter);
	<T> LONG_CLAUSE sqrt(ISupplierLong getter);
	
	ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt();
	
}
