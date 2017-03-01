package com.neaterbits.query.sql.dsl.api;

interface ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT>

	extends ISQLJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>,
			
			ISQLLogical_Where_MultiMapped_Alias<MODEL, RESULT> {

}
