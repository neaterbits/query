package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Multi_Alias<MODEL, RESULT> 
	extends 
	
		ISharedMap_Initial_NoParam_Alias<MODEL, RESULT, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,  
		ISQLLogical_WhereOrJoin_MultiMapped_Alias_And_Function<MODEL, RESULT, IShortJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>,
		IShortJoin_MultiMapped_Alias_Initial<MODEL, RESULT>
{

}
