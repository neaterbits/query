package com.neaterbits.query.sql.dsl.api;

interface ISQLJoin_Condition_MultiMapped_Alias<
		MODEL,
		RESULT,
		JOIN_CONDITION extends ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT, JOIN_CONDITION>>

	extends ISQLJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			JOIN_CONDITION>,
			
			ISQLLogical_Where_MultiMapped_Alias<MODEL, RESULT> {

}
