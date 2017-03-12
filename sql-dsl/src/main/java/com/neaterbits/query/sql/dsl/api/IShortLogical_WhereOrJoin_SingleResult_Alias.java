package com.neaterbits.query.sql.dsl.api;

public interface IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>
	extends 
		ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		IShortJoin_Condition_SingleResult_Alias<MODEL, RESULT>,
		ISQLLogical_Where_SingleResult_Alias<MODEL, RESULT> {

	
}
