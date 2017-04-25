package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_NoParam_Alias<

		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,

		LENGTH_RET_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
		> 
	extends ISharedFunctions_String_NoParam_Base<
			MODEL,
			RESULT,
			ISharedFunctions_String_Alias<MODEL, RESULT, RET, LENGTH_RET_CLAUSE, LENGTH_RET_CLAUSE>,
			ISharedFunctions_String_Alias<MODEL, RESULT, RET, LENGTH_RET_CLAUSE, STRING_CLAUSE>
	> {

}
