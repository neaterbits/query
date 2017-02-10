package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_MultiEntity_Alias<MODEL, RESULT>

	extends IClassicJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			IClassicJoin_Condition_MultiEntity_Alias<MODEL, RESULT>>,
			
			
			IClassicLogical_Where_MultiEntity_Alias<MODEL, RESULT>
			
			{
	
}
