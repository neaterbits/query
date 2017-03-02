package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>

	extends 
		ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		ISQLJoin_Alias<MODEL,RESULT, ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT>>,
		ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT>,
        ISQLLogical_Where_SingleResult_Alias<MODEL, RESULT> {

}
