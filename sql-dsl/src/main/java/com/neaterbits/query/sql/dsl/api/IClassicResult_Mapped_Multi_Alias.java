package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>
	extends IClassicMultiSelectSourceBuilder<MODEL, RESULT>,
	
			IClassic_From_MultiMapped_Alias<MODEL, RESULT>,
	
		    ISharedMap_Initial_Alias<MODEL, RESULT, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>> {

}
