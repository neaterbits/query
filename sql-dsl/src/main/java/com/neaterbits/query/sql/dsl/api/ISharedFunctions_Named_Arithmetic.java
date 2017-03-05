package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_Arithmetic<
			MODEL,
			RESULT,
			RET, // can be used for mapping as well extends ISharedLogical_Base<MODEL, RESULT>,
			
			// comment out since may be used for map as well, not only in conditions
			INTEGER_CLAUSE, //extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
			LONG_CLAUSE  //extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>

> {
			

	<T> INTEGER_CLAUSE abs(IFunctionInteger<T> getter);
	<T> LONG_CLAUSE abs(IFunctionLong<T> getter);

	<T> INTEGER_CLAUSE sqrt(IFunctionInteger<T> getter);
	<T> LONG_CLAUSE sqrt(IFunctionLong<T> getter);
	
}
