package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiEntity_Alias<
			MODEL,
			RESULT,
			JOIN_CONDITION extends ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT, JOIN_CONDITION>>

		extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
				ISQLJoin_MultiEntity_Alias<MODEL, RESULT>,
				ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT, JOIN_CONDITION>,
				ISQLLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>,
		        ISQLLogical_Where_MultiEntity_Alias<MODEL, RESULT>,
		        ISharedProcessResult_OrderBy_Mapped_Alias<MODEL, RESULT> {

}
