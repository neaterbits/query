package com.neaterbits.query.sql.dsl.api;

interface IClassicJoin_Condition_ProcessResult_Alias<MODEL, RESULT>

	extends IClassicJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			IClassicJoin_Condition_ProcessResult_Alias<MODEL, RESULT>>,
			
			IClassicLogical_Where_ProcessResult_Alias<MODEL, RESULT> {

}
