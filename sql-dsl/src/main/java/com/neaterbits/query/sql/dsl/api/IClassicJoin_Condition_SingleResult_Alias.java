package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_SingleResult_Alias<MODEL, RESULT>

	extends 
	
		ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT, IClassicJoin_Condition_SingleResult_Alias<MODEL, RESULT>>,
	
		// for adding more joins at top-level
		IClassicJoin_Alias<MODEL, RESULT, IClassicJoin_Condition_SingleResult_Alias<MODEL, RESULT>> {

}
