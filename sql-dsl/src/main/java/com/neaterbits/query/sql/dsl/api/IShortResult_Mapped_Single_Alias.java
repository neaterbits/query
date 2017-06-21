package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Single_Alias<MODEL, RESULT>

	extends

		ISharedResultMap_Initial_NoParam_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,  
		ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
		IShortJoin_Single_Alias_Initial<MODEL, RESULT>

	{

}
