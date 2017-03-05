package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_All<
		MODEL,
		RESULT,
		
		RET,
		
		// comment out since may be used for map as well, not only in conditions
		INTEGER_CLAUSE,
		LONG_CLAUSE,
		STRING_CLAUSE
	>
	extends
		ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE>,
		ISharedFunctions_Named_String<MODEL, RESULT, RET, STRING_CLAUSE> {
	


}
