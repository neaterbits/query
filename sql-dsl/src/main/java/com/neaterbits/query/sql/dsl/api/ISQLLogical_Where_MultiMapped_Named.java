package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Where_MultiMapped_Named<MODEL, RESULT>
		extends ISQLLogical_Where_Named_Base<
			MODEL,
			RESULT,
			ISQLLogical_AndOr_MultiMapped_Named<MODEL,RESULT>> {

}
