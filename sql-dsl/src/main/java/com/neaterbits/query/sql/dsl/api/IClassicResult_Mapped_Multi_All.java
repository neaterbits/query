package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Mapped_Multi_All<MODEL, RESULT>
	extends 
		IClassicMultiSelectSourceBuilder<MODEL, RESULT>,
		
		ISharedMap_Initial_NoParam_Undecided1<
			MODEL,
			RESULT,
			IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
			IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>,
			IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>> {

}
