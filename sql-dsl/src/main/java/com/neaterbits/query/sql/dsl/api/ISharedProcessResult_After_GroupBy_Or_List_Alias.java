package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT>
	extends ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>,
			ISharedProcessResult_List_Alias<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT>>{

}
