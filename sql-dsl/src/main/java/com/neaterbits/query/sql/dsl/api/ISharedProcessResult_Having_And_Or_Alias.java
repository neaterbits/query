package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>

		extends 
					ISharedLogical_Base<MODEL, RESULT>,
					ISharedProcessResult_OrderBy_Alias_Base<MODEL, RESULT>,
		
				// We can nest having-clauses like we nest regular clauses, so just reuse same nesting mechanism
			    // This is true for JPQL at least where is same grammar for select conditions and having conditions 
		
				ISharedLogical_And_Or_Alias<
					MODEL,RESULT,
					ISharedProcessResult_Having_And_Alias<MODEL, RESULT>,
					ISharedProcessResult_Having_Or_Alias<MODEL, RESULT>,
					ISharedProcessResult_Having_And_Nested_Alias<MODEL, RESULT>,
					ISharedProcessResult_Having_Or_Nested_Alias<MODEL, RESULT>> {

}
