package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_Transform_Initial<

		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		INTEGER_CLAUSE  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_CLAUSE		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_CLAUSE 	extends ISharedFunction_Next<MODEL, RESULT, RET>
	>

	extends ISharedFunctions_Inital_Base<MODEL, RESULT>,
				ISharedFunctions_Named_All_Transform<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> {
    
}
