package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>

	extends ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
	
		IClassicJoin_Alias<MODEL, RESULT, IClassicJoin_Condition_SingleResult_Alias<MODEL, RESULT>>{

}
