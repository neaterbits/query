package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Single<MODEL, RESULT>
		// Must map both from entity and other
		extends IClassicResult_Mapped_Single_All<MODEL, RESULT>
		// and also entity-mapping if goes straight to from()
		
	{

}
