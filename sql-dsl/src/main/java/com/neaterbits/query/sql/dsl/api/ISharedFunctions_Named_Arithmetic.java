package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_Arithmetic<
			MODEL,
			RESULT,
			RET extends ISharedLogical_Base<MODEL, RESULT>,
			
			COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, RET>> {
			

		<T> COMPARABLE_CLAUSE abs(IFunctionInteger<T> getter);
		<T> COMPARABLE_CLAUSE abs(IFunctionLong<T> getter);
		ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> abs();


		<T> COMPARABLE_CLAUSE sqrt(IFunctionInteger<T> getter);
		<T> COMPARABLE_CLAUSE sqrt(IFunctionLong<T> getter);
		
		ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> sqrt();
	
}
