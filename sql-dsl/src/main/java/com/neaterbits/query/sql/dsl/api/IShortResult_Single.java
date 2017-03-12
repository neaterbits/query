package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Single<MODEL, RESULT> 
	extends 
		IShortResult_Base<MODEL, RESULT>,
		IShortResult_Mapped_Single_All<MODEL, RESULT>,

		
		
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT>,
		IShortLogical_WhereOrJoin_SingleResult_Entity_Alias<MODEL, RESULT> {

}
