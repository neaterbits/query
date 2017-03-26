package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Entity_Multi_Alias<MODEL, RESULT>

	extends ISQLLogical_WhereOrJoin_MultiEntity_Alias_And_Function<MODEL, RESULT, IShortJoin_Condition_MultiEntity_Alias<MODEL, RESULT>>,
			IShortJoin_Multi_Alias_Initial<MODEL, RESULT>

	{

}
