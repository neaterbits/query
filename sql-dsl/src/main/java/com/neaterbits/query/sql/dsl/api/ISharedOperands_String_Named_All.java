package com.neaterbits.query.sql.dsl.api;

public interface ISharedOperands_String_Named_All<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COMMON_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> extends ISharedOperands_String_Named_Base<MODEL, RESULT, RET, NAMED_TYPE_RET>,
			  ISharedOperands_String_Common<MODEL, RESULT, RET, COMMON_TYPE_RET> {
	

}