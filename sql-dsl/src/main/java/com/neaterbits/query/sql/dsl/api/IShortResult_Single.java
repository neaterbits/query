package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Single<MODEL, RESULT> 
	extends IShortResult_Mapped_Single_All<MODEL, RESULT>,
	
	ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
	ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> {

}
