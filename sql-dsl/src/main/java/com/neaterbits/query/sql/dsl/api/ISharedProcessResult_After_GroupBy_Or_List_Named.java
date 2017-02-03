package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT>
		extends ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>,
				ISharedProcessResult_List_Named<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>{

}
