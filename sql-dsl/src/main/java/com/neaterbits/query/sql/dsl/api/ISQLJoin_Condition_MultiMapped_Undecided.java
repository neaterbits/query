package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_Condition_MultiMapped_Undecided<
		MODEL,
		RESULT,
		
		NAMED_LEFT,
		NAMED_RIGHT,
		NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, NAMED_LEFT, NAMED_RIGHT, NAMED_JOIN_CONDITION>,
		
		ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT, ALIAS_JOIN_CONDITION>>
	
	extends 
		ISQLJoin_Condition_Named_Base<
			MODEL,
			RESULT,
			
			NAMED_LEFT,
			NAMED_RIGHT,
			NAMED_JOIN_CONDITION>,
			
		ISQLJoin_Condition_Alias_Base<
			MODEL,
			RESULT,
			ALIAS_JOIN_CONDITION>,
			
			

		ISQLLogical_Where_MultiMapped_Undecided<MODEL, RESULT> {
	
}
