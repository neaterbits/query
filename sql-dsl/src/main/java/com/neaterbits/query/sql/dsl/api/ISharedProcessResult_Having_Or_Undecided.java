package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_Having_Or_Undecided<MODEL, RESULT> 

	extends 
		ISharedLogical_Or_Undecided_Base<
			MODEL, RESULT,
			ISharedProcessResult_Having_Or_Named<MODEL, RESULT>,
			ISharedProcessResult_Having_And_Nested_Named<MODEL, RESULT>,
			ISharedProcessResult_Having_Or_Alias<MODEL, RESULT>,
			ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>
			>,

		ISharedProcessResult_OrderBy_Named_Base<MODEL, RESULT>,
		ISharedProcessResult_OrderBy_Alias_Base<MODEL, RESULT>,
		
		ISharedCompileEndClause<MODEL> {

}
