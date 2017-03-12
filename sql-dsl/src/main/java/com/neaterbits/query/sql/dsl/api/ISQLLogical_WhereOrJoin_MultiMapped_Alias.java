package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiMapped_Alias<
		MODEL,
		RESULT,
		JOIN_CONDITION extends ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT, JOIN_CONDITION>>

	extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>, 

		ISQLLogical_Where_MultiMapped_Alias<MODEL, RESULT>,
		ISQLJoin_MultiMapped_Alias<MODEL, RESULT>,
		ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT, JOIN_CONDITION>,
		ISQLLogical_AndOr_MultiMapped_Alias<MODEL, RESULT>,
		ISQLProcessResult_Alias<MODEL, RESULT> {

}
