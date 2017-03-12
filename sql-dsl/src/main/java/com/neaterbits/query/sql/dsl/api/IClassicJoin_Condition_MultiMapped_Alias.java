package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_MultiMapped_Alias<MODEL, RESULT>
	extends 
	
		ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT, IClassicJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>,
		IClassicJoin_Alias<MODEL, RESULT, IClassicJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>{

}
