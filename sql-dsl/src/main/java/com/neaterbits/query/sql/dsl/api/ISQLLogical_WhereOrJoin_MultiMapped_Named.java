package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>

	extends 
		ISQLLogical_Where_MultiMapped_Named<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
		ISQLProcessResult_Named<MODEL, RESULT> {

}
