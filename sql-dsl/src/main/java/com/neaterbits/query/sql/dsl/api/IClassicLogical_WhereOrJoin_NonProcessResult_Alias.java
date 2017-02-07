package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_NonProcessResult_Alias<MODEL, RESULT>

		extends IClassicLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
				IClassicJoin_Alias<MODEL,RESULT, IClassicJoin_Condition_NonProcessResult_Alias<MODEL, RESULT>>,
				IClassicJoin_Condition_NonProcessResult_Alias<MODEL, RESULT>,
				IClassicLogical_AndOr_NonProcessResult_Alias<MODEL, RESULT>,
		        IClassicLogical_Where_NonProcessResult_Alias<MODEL, RESULT> {

}
