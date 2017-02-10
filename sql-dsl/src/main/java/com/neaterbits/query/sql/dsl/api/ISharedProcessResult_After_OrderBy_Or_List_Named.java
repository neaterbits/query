package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT>
			extends ISharedProcessResult_List_Named<MODEL, RESULT, ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT>>,
					ISharedCompileEndClause<MODEL>{
	
}
