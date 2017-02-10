package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>

		extends IClassicLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
				IClassicJoin_MultiEntity_Alias<MODEL, RESULT>,
				IClassicJoin_Condition_MultiEntity_Alias<MODEL, RESULT>,
				IClassicLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>,
		        IClassicLogical_Where_MultiEntity_Alias<MODEL, RESULT> {

}
