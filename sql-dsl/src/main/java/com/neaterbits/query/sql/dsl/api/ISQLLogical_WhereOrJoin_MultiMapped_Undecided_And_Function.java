package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiMapped_Undecided_And_Function<MODEL, RESULT, JOIN_CONDITION extends ISQLJoin_Condition_MultiMapped_Undecided<MODEL, RESULT, JOIN_CONDITION>>
	extends ISQLLogical_WhereOrJoin_MultiMapped_Undecided<MODEL, RESULT, JOIN_CONDITION>,
		    ISharedLogical_Where_Undecided_Function<MODEL, RESULT, ISQLLogical_AndOr_MultiMapped_Undecided<MODEL, RESULT>> {

}
