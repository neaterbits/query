package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>
	extends
	
		ISharedLogical_And_Alias_Base<
			MODEL,
			RESULT,
			ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			
		ISharedLogical_And_Alias_Function<
			MODEL,
			RESULT,
			ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			
		ISharedProcessResult_All_Alias<MODEL, RESULT>,

		ISharedCompileEndClause<MODEL> {

}
