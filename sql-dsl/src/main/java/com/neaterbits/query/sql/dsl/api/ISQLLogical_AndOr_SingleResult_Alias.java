package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>
		extends
			ISharedLogical_And_Or_Alias<
					MODEL, 
					RESULT,
					ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Alias <MODEL, RESULT>,
					
					ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Alias <MODEL, RESULT>>,
			
			ISharedCompileEndClause<MODEL> {
}
