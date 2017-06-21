package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Single_Named<MODEL, RESULT>
	extends

		ISharedMap_Initial_NoParam_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL,RESULT>>,
		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
		IShortJoin_Single_Named_Initial<MODEL, RESULT>
{

	
}
