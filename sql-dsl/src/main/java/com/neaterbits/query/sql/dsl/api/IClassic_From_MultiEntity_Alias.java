package com.neaterbits.query.sql.dsl.api;

public interface IClassic_From_MultiEntity_Alias<MODEL, RESULT>
	extends IClassic_From_Alias_Base<
			MODEL, 
			RESULT,
			IClassicLogical_WhereOrJoin_MultiEntity_Alias<MODEL,RESULT>> {
	
}
