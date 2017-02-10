package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_All_Alias<MODEL, RESULT> 
		extends ISharedProcessResult_GroupBy_Alias<MODEL, RESULT>,
				ISharedProcessResult_OrderBy_Mapped_Alias<MODEL, RESULT> {

}
