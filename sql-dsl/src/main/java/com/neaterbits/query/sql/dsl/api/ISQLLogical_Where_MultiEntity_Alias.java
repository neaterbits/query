package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Where_MultiEntity_Alias<MODEL, RESULT>

	extends ISQLLogical_Where_Alias_Base<
			MODEL,
			RESULT,
			ISQLLogical_AndOr_MultiEntity_Alias<MODEL,RESULT>>,
	
	ISQLJoin_MultiEntity_Alias<MODEL, RESULT>{

}
