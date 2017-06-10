package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_After_GroupBy_Undecided<MODEL, RESULT> 
	extends
		ISharedProcessResult_Having_Named_Base<MODEL, RESULT>,
		ISharedProcessResult_Having_Alias_Base<MODEL, RESULT>,
		ISharedProcessResult_OrderBy_Mapped_Undecided<MODEL, RESULT> {

	ISharedProcessResult_Having_Aggregate_Undecided<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Undecided<MODEL, RESULT>> having();

}
