package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_All_Named<MODEL, RESULT> 
		extends ISharedProcessResult_GroupBy_Named<MODEL, RESULT>,
				ISharedProcessResult_OrderBy_Mapped_Named<MODEL, RESULT> {

}
