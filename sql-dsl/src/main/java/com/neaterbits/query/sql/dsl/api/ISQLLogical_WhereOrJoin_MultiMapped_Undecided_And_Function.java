package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiMapped_Undecided_And_Function<
		MODEL,
		RESULT,
		NAMED_LEFT,
		NAMED_RIGHT,
		NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, NAMED_LEFT, NAMED_RIGHT, NAMED_JOIN_CONDITION>,
		ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT, ALIAS_JOIN_CONDITION>
	>
	extends ISQLLogical_WhereOrJoin_MultiMapped_Undecided<MODEL, RESULT, NAMED_LEFT, NAMED_RIGHT, NAMED_JOIN_CONDITION, ALIAS_JOIN_CONDITION>,
		    ISharedLogical_Where_Undecided_Function<
		    	MODEL,
		    	RESULT,
		    	
		    	ISQLLogical_AndOr_MultiMapped_Named<MODEL, RESULT>,
		    	ISQLLogical_AndOr_MultiMapped_Alias<MODEL, RESULT>,
		    	ISQLLogical_AndOr_MultiMapped_Undecided<MODEL, RESULT>> {

}
