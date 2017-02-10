package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_Where_MultiEntity_Alias<MODEL, RESULT>

	extends IClassicLogical_Where_Alias_Base<
			MODEL,
			RESULT,
			IClassicLogical_AndOr_MultiEntity_Alias<MODEL,RESULT>>,
	
	IClassicJoin_MultiEntity_Alias<MODEL, RESULT>{

}
