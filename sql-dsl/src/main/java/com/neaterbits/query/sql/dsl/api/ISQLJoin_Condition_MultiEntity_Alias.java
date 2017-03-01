package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT>

	extends ISQLJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT>>,
			
			
			ISQLLogical_Where_MultiEntity_Alias<MODEL, RESULT>
			
			{
	
}
