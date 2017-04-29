package com.neaterbits.query.sql.dsl.api;

public interface ISharedOperands_String_Named_All<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> extends ISharedOperands_String_Named_Base<MODEL, RESULT, RET, TYPE_RET>,
			  ISharedOperands_String_Common<MODEL, RESULT, RET, TYPE_RET> {
	

}