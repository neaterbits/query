package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Multi_All<MODEL, RESULT> 

	extends ISharedResultMapper_All<
			MODEL,
			
			RESULT, 
			IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
			IShortResult_Mapped_Multi_Alias<MODEL, RESULT>,
			IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>> {

}
