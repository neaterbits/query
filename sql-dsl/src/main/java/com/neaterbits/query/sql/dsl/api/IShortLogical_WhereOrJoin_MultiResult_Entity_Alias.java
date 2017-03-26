package com.neaterbits.query.sql.dsl.api;

public interface IShortLogical_WhereOrJoin_MultiResult_Entity_Alias<MODEL, RESULT> 
	extends 
		ISQLLogical_WhereOrJoin_MultiEntity_Alias_And_Function<MODEL, RESULT, IShortJoin_Condition_MultiEntity_Alias<MODEL, RESULT>>,
		ISQLLogical_Where_MultiEntity_Alias<MODEL, RESULT>
	{

}
