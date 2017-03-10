package com.neaterbits.query.sql.dsl.api;

public interface ISharedOperands_String_Common<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
		
	> {

	<T> TYPE_RET concat(String value);

}
