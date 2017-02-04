package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_AndOr_ProcessResult_Alias<MODEL, RESULT> 
	extends
	ISharedLogical_And_Or_Alias<
			MODEL, 
			RESULT,

			IClassicLogical_And_ProcessResult_Alias<MODEL, RESULT>,
			IClassicLogical_Or_ProcessResult_Alias<MODEL, RESULT>,

			IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>
			>,
	
	ISharedCompileEndClause<MODEL> {
}
