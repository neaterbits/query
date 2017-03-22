package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_After_OrderBy_Or_List_Alias<MODEL, RESULT>
	extends ISharedProcessResult_List_Alias<MODEL, RESULT, ISharedProcessResult_OrderBy_AfterSortOrder_Alias<MODEL, RESULT>>,
	ISharedCompileEndClause<MODEL>{


}
