package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_AndOr_MultiMapped_Alias<MODEL, RESULT> 
	extends
	ISharedLogical_And_Or_Alias<
			MODEL, 
			RESULT,

			IClassicLogical_And_MultiMapped_Alias<MODEL, RESULT>,
			IClassicLogical_Or_MultiMapped_Alias<MODEL, RESULT>,

			IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>
			>,
	
    ISharedProcessResult_All_Alias<MODEL, RESULT>,
	ISharedCompileEndClause<MODEL> {
}
