package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_AndOr_MultiMapped_Undecided<MODEL, RESULT> 
	extends
		ISharedLogical_And_Or_Undecided<
				MODEL, 
				RESULT,

				ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>,
				ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>,
		
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
		
				ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>,
				ISQLLogical_Or_MultiMapped_Alias<MODEL, RESULT>,
				
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,

				ISQLLogical_And_MultiMapped_Undecided<MODEL, RESULT>,
				ISQLLogical_Or_MultiMapped_Undecided<MODEL, RESULT>,
		
				ISQLLogical_And_NonProcessResult_Undecided<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Undecided<MODEL, RESULT>
				>,
		
		ISharedProcessResult_All_Undecided<MODEL, RESULT>,
		ISharedCompileEndClause<MODEL> {
}
