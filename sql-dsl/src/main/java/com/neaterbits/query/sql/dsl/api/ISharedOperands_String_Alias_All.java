package com.neaterbits.query.sql.dsl.api;

public interface ISharedOperands_String_Alias_All<

		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		ALIAS_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COMMON_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>

	> extends ISharedOperands_String_Alias_Base<MODEL, RESULT, RET, ALIAS_TYPE_RET>,
			  ISharedOperands_String_Common<MODEL, RESULT, RET, COMMON_TYPE_RET> {

}
