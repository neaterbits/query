package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>

	extends ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
			ISharedLogical_Where_Named_Function<MODEL, RESULT, ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>> {

}
