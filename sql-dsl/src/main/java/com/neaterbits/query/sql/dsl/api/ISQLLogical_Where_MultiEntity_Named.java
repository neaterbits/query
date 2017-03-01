package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Where_MultiEntity_Named<MODEL, RESULT>
		extends ISQLLogical_Where_Named_Base<
			MODEL,
			RESULT,
			ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>> {

}
