package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_AndOr_MultiMapped_Alias<MODEL, RESULT> 
	extends
	ISharedLogical_And_Or_Alias<
			MODEL, 
			RESULT,

			ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>,
			ISQLLogical_Or_MultiMapped_Alias<MODEL, RESULT>,

			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>
			>,
	
    ISharedProcessResult_All_Alias<MODEL, RESULT>,
	ISharedCompileEndClause<MODEL> {
}
