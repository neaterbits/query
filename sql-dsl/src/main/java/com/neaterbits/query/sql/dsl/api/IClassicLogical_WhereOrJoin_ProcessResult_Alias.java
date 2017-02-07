package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_ProcessResult_Alias<MODEL, RESULT> 
	extends IClassicLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>, 

		IClassicLogical_Where_ProcessResult_Alias<MODEL, RESULT>,
		IClassicJoin_ProcessResult_Alias<MODEL, RESULT>,
		IClassicJoin_Condition_ProcessResult_Alias<MODEL, RESULT>,
		IClassicLogical_AndOr_ProcessResult_Alias<MODEL, RESULT>,
		IClassicProcessResult_Alias<MODEL, RESULT> {

}
