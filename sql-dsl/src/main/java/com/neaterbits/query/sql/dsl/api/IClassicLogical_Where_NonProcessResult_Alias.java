package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_Where_NonProcessResult_Alias<MODEL, RESULT>
		extends IClassicLogical_Where_Alias_Base<MODEL, RESULT, IClassicLogical_And_Or_Alias<MODEL,RESULT>>,
		
		IClassicJoin_NonProcessResult_Alias<MODEL, RESULT>{

}
