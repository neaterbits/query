package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Mapped_Multi_Named<MODEL, RESULT>

	extends IClassicMultiSelectSourceBuilder<MODEL, RESULT>,
	
			IClassic_From_MultiMapped_Named<MODEL, RESULT>,
	
			ISharedMap_Initial_Named<MODEL, RESULT, IClassicResult_Mapped_Multi_Named<MODEL,RESULT>>{

}
