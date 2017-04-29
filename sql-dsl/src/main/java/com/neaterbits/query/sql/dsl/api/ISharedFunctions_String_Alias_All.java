package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_Alias_All<

	MODEL,
	RESULT,
	
	RET extends ISharedFunction_After<MODEL, RESULT>,
	
	LENGTH_RET_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
	STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	>
	
	extends ISharedFunctions_String_Alias_Base<
		MODEL,
		RESULT,
		
		RET,
		
		LENGTH_RET_CLAUSE,
		STRING_CLAUSE>	{

}
