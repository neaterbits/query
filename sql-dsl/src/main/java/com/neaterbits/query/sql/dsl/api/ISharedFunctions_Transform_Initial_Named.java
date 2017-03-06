package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Transform_Initial_Named<

		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>
	>

	extends ISharedFunctions_Inital_Base<MODEL, RESULT>,
				ISharedFunctions_All_Transform_Named<MODEL, RESULT, RET, INTEGER_RET, LONG_RET, DOUBLE_RET, STRING_RET> {
    
}
