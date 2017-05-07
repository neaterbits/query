package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Or_NonProcessResult_Undecided<MODEL, RESULT>
	extends
	
		ISharedLogical_Or_Undecided_Base<
					MODEL,
					RESULT,
					ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
					ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
					ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,
					
		ISharedLogical_Or_Undecided_Function<
					MODEL,
					RESULT,
					ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
					ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
					ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Undecided<MODEL, RESULT>,
					ISQLLogical_And_NonProcessResult_Undecided<MODEL, RESULT>>,
		
		ISharedCompileEndClause<MODEL> {
		
}
