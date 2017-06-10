package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_Having_Undecided<MODEL, RESULT>
	extends ISharedProcessResult_Having_Named_Base<MODEL, RESULT>,
			ISharedProcessResult_Having_Alias_Base<MODEL, RESULT>,

			ISharedProcessResult_Base<MODEL, RESULT> {

	ISharedProcessResult_Having_Aggregate_Named<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>> having();
	
}
