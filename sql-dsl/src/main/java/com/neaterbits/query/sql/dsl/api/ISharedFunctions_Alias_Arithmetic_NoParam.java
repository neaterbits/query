package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_Arithmetic_NoParam<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,

		INTEGER_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_CLAUSE	   extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		> 
	extends ISharedFunctions_Arithmetic_NoParam_Base<
			MODEL,
			RESULT,
			ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE>
	> {

}
