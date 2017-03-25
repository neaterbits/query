package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Single<MODEL, RESULT> 
	extends 
		IShortResult_Base<MODEL, RESULT>,
		IShortResult_Mapped_Single_All<MODEL, RESULT>,

		
		ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
		IShortJoin_Single_Named_Initial<MODEL, RESULT>,
		
		ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>


		
/*
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named_Initial<MODEL, RESULT>,
		IShortLogical_WhereOrJoin_SingleResult_Entity_Alias<MODEL, RESULT> 
		
*/
{

}
