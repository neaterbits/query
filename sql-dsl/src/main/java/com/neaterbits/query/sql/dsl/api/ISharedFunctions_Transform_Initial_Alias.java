package com.neaterbits.query.sql.dsl.api;

@Deprecated // swap out
public interface ISharedFunctions_Transform_Initial_Alias <
		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		LENGTH_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,

		BYTE_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SHORT_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGINTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		FLOAT_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		>

		extends
			ISharedFunctions_Initial_Base<MODEL, RESULT>,
			ISharedFunctions_Transform_Alias_All<MODEL, RESULT, RET, LENGTH_RET, BYTE_RET, SHORT_RET, INTEGER_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET> {

}
