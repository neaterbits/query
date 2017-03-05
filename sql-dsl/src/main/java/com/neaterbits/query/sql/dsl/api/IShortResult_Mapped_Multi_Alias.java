package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Multi_Alias<MODEL, RESULT> 
	extends 
	
		ISharedResult_Mapped_Alias_Base<MODEL, RESULT, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,  
	
		ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>
{

}
