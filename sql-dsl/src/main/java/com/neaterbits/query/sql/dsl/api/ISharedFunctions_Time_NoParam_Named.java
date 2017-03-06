package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Time_NoParam_Named<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		DATE_CLAUSE 	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
		TIMESTAMP_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
	>

	{

		DATE_CLAUSE today();

		TIMESTAMP_CLAUSE current();

}
