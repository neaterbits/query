package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiMapped_Undecided<
		MODEL,
		RESULT,
		NAMED_LEFT,
		NAMED_RIGHT,
		NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, NAMED_LEFT, NAMED_RIGHT, NAMED_JOIN_CONDITION>,
		ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT, ALIAS_JOIN_CONDITION>
	>
	
	extends ISQLLogical_WhereOrJoin_Undecided_Base<MODEL, RESULT>, 
	
	ISQLLogical_Where_MultiMapped_Undecided<MODEL, RESULT>,
	ISQLJoin_MultiMapped_Undecided<MODEL, RESULT>,
	ISQLJoin_Condition_MultiMapped_Undecided<MODEL, RESULT, NAMED_LEFT, NAMED_RIGHT, NAMED_JOIN_CONDITION, ALIAS_JOIN_CONDITION>,
	ISQLLogical_AndOr_MultiMapped_Undecided<MODEL, RESULT>,
	ISQLProcessResult_Undecided<MODEL, RESULT> {

}
