package com.neaterbits.query.sql.dsl.api;

interface ISQLLogical_And_NoOp_Named<MODEL, RESULT> 
	extends ISharedLogical_And_Named_All<
		MODEL,
		RESULT,
		ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
		ISQLLogical_Or_NoOp_Named<MODEL, RESULT>> {

}
