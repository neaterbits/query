package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_Undecided<
		MODEL,
		RESULT,
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_LENGTH_RET_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		ALIAS_LENGTH_RET_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
		
		> 

	extends ISharedFunctions_String_Named_All<MODEL, RESULT, NAMED_RET, NAMED_LENGTH_RET_CLAUSE, NAMED_STRING_CLAUSE>,
			ISharedFunctions_String_Alias_All<MODEL, RESULT, ALIAS_RET, ALIAS_LENGTH_RET_CLAUSE, ALIAS_STRING_CLAUSE> {
	
}