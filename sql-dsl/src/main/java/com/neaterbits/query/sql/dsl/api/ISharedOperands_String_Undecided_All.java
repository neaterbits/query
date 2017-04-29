package com.neaterbits.query.sql.dsl.api;


public interface ISharedOperands_String_Undecided_All<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		ALIAS_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		UNDECIDED_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> extends ISharedOperands_String_Named_Base<MODEL, RESULT, RET, NAMED_TYPE_RET>,
			  ISharedOperands_String_Alias_Base<MODEL, RESULT, RET, ALIAS_TYPE_RET>,
			  ISharedOperands_String_Common<MODEL, RESULT, RET, UNDECIDED_TYPE_RET> {


				  // TODO concatOf
}
