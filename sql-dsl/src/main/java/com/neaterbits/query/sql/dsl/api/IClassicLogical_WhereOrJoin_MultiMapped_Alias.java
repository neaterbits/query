package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT> 
	extends IClassicLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>, 

		IClassicLogical_Where_MultiMapped_Alias<MODEL, RESULT>,
		IClassicJoin_MultiMapped_Alias<MODEL, RESULT>,
		IClassicJoin_Condition_MultiMapped_Alias<MODEL, RESULT>,
		IClassicLogical_AndOr_MultiMapped_Alias<MODEL, RESULT>,
		IClassicProcessResult_Alias<MODEL, RESULT> {

}
