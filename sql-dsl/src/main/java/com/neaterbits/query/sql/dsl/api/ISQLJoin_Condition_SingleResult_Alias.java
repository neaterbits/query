package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_Condition_SingleResult_Alias<
	MODEL, 
	RESULT,
	JOIN_CONDITION extends ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT, JOIN_CONDITION>>

	extends ISQLJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			JOIN_CONDITION>,
			
			
			ISQLLogical_Where_SingleResult_Alias<MODEL, RESULT>
			
			{

}
