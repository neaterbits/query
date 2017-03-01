package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Multi<MODEL, RESULT>
	extends IClassicResult_Mapped_Multi_All<MODEL, RESULT>,
		IClassic_From_MultiEntity_Named<MODEL, RESULT>,
		IClassic_From_MultiEntity_Alias<MODEL, RESULT> {


}
