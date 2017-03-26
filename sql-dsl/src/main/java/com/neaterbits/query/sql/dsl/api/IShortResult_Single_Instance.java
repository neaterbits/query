package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Single_Instance<MODEL, RESULT> 
	extends 
		IShortResult_Base<MODEL, RESULT>,
		IShortResult_Mapped_Single_All<MODEL, RESULT>,

		
		ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
		IShortJoin_Single_Named_Initial<MODEL, RESULT>
		
		/* NEVER join direct on alias, only if mapped via alias because entity queries should be mapped by themselves
		,ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>,
		IShortJoin_Single_Alias_Initial<MODEL, RESULT>
		*/


		
/*
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named_Initial<MODEL, RESULT>,
		IShortLogical_WhereOrJoin_SingleResult_Entity_Alias<MODEL, RESULT> 
		
*/
{

}
