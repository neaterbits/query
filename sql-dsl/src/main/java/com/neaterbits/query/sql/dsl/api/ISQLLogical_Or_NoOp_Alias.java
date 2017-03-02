package com.neaterbits.query.sql.dsl.api;

interface ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>
	extends ISharedLogical_Or_Alias_Base<
		MODEL,
		RESULT,
		ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>,
		ISQLLogical_And_NoOp_Alias<MODEL, RESULT>>
	{

}
