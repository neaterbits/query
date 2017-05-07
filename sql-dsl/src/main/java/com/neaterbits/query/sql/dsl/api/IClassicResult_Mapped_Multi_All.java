package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Mapped_Multi_All<MODEL, RESULT>
	extends 
		IClassicMultiSelectSourceBuilder<MODEL, RESULT>,
		
		ISharedResultMapper_Undecided<
			MODEL,
			RESULT,
			IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
			IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>,
			IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>> {

}
