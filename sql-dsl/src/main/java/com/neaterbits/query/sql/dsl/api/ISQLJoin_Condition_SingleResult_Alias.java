package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT>

	extends ISQLJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT>>,
			
			
			ISQLLogical_Where_SingleResult_Alias<MODEL, RESULT>
			
			{

}
