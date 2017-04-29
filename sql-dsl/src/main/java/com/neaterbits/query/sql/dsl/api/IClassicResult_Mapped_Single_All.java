package com.neaterbits.query.sql.dsl.api;


public interface IClassicResult_Mapped_Single_All<MODEL, RESULT>
	extends ISharedSingleSelectSourceBuilder<MODEL, RESULT>,
			ISharedResultMapper_All<
					MODEL,
					RESULT,
					IClassicResult_Mapped_Single_Named<MODEL, RESULT>, 
					IClassicResult_Mapped_Single_Alias<MODEL, RESULT>,
					IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>> {

}
