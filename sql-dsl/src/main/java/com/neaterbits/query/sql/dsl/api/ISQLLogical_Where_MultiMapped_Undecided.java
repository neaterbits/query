package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Where_MultiMapped_Undecided<MODEL, RESULT>
	extends ISQLLogical_Where_Undecided_Base<
		MODEL,
		RESULT,
		ISQLLogical_AndOr_MultiMapped_Named<MODEL,RESULT>,
		ISQLLogical_AndOr_MultiMapped_Alias<MODEL,RESULT>> {

}
