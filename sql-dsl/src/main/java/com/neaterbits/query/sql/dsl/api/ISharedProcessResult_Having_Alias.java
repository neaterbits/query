package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_Having_Alias<MODEL, RESULT>

		extends 
			ISharedProcessResult_Having_Alias_Base<MODEL, RESULT>,
			ISharedProcessResult_Base<MODEL, RESULT> {

	ISharedProcessResult_Having_Aggregate_Alias<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>> having();

}
