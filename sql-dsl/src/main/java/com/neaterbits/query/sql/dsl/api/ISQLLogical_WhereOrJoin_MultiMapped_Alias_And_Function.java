package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiMapped_Alias_And_Function<MODEL, RESULT, JOIN_CONDITION extends ISQLJoin_Condition_MultiMapped_Alias<MODEL, RESULT, JOIN_CONDITION>>
	extends ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT, JOIN_CONDITION>,
		    ISharedLogical_Where_Alias_Function<MODEL, RESULT, ISQLLogical_AndOr_MultiMapped_Alias<MODEL, RESULT>> {

}
