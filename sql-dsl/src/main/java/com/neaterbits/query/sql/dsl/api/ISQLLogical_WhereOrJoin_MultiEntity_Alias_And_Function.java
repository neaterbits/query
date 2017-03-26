package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiEntity_Alias_And_Function<
			MODEL,
			RESULT,
			JOIN_CONDITION extends ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT, JOIN_CONDITION>>

	extends ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT, JOIN_CONDITION>

	// TODO: include after resolving conflicts
	// ISharedLogical_Where_Alias_Function<MODEL, RESULT, ISQLLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>>

{

}
