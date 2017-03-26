package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_MultiEntity_Alias<MODEL, RESULT> 
	extends 
		
		ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT, IClassicJoin_Condition_MultiEntity_Alias<MODEL, RESULT>>,
		IClassicJoin_Alias<MODEL, RESULT, IClassicJoin_Condition_MultiEntity_Alias<MODEL, RESULT>>{

}
