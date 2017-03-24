package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>

		extends ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>,
				ISharedLogical_Where_Alias_Function<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>> {

}
