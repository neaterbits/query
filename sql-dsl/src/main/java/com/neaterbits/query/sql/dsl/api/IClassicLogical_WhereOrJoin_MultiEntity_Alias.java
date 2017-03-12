package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>
	extends ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>,
		IClassicJoin_Alias<MODEL, RESULT, ISQLJoin_Condition_MultiEntity_Alias<MODEL,RESULT>> {

}
