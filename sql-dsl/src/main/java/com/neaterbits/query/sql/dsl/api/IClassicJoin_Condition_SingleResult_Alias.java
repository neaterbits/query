package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_SingleResult_Alias<MODEL, RESULT>

	extends IClassicJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			IClassicJoin_Condition_SingleResult_Alias<MODEL, RESULT>>,
			
			
			IClassicLogical_Where_SingleResult_Alias<MODEL, RESULT>
			
			{

}
