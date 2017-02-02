package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_Arithmetic<
			MODEL,
			RESULT,
			RET extends ISharedLogical_Base<MODEL, RESULT>,
			
			INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
			LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>> {
			

		<T> INTEGER_CLAUSE abs(IFunctionInteger<T> getter);
		<T> LONG_CLAUSE abs(IFunctionLong<T> getter);
		
		ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs();


		<T> INTEGER_CLAUSE sqrt(IFunctionInteger<T> getter);
		<T> LONG_CLAUSE sqrt(IFunctionLong<T> getter);
		
		ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt();
	
}
