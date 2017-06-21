package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Mapped_Single_Alias<MODEL, RESULT>
	extends ISharedSingleSelectSourceBuilder<MODEL, RESULT>,
		    IClassic_From_SingleResult_Alias<MODEL, RESULT>,
			ISharedMap_Initial_Alias<MODEL, RESULT, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>> {

}
