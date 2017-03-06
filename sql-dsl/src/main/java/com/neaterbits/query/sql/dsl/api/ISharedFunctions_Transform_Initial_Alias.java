package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Transform_Initial_Alias <
		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		INTEGER_CLAUSE	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_CLAUSE		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_CLAUSE	extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		>

		extends
			ISharedFunctions_Inital_Base<MODEL, RESULT>,
			ISharedFunctions_All_Transform_Alias<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> {

}
