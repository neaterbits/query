package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>
	extends ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT, IClassicJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>,
	
		IClassicJoin_Alias<MODEL, RESULT, IClassicJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>{

}
