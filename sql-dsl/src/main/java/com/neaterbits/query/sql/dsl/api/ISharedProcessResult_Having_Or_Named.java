package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_Having_Or_Named<MODEL, RESULT>
		extends ISharedLogical_Or_Named_All<
			MODEL,
			RESULT,
			
			ISharedProcessResult_Having_Or_Named<MODEL, RESULT>,
			ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>>,
			
			ISharedProcessResult_OrderBy_Named_Base<MODEL, RESULT>,
			
			ISharedCompileEndClause<MODEL> {

}
