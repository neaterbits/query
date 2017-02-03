package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_All_Named<MODEL, RESULT> 
		extends ISharedProcessResult_GroupBy_Named<MODEL, RESULT>,
				ISharedProcessResult_Having_Named<MODEL, RESULT>,
				ISharedProcessResult_OrderBy_Named<MODEL, RESULT> {

}
