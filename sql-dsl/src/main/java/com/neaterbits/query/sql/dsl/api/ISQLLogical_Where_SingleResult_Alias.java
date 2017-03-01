package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Where_SingleResult_Alias<MODEL, RESULT>
		
		extends ISQLLogical_Where_Alias_Base<
				MODEL,
				RESULT,
				ISQLLogical_AndOr_SingleResult_Alias<MODEL,RESULT>>,
		
		ISQLJoin_NonProcessResult_Alias<MODEL, RESULT>{

}
