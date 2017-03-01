package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>
	extends
	ISharedLogical_And_Or_Named_All<
			MODEL, 
			RESULT,
			ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_Or_MultiEntity_Named <MODEL, RESULT>,
			
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named <MODEL, RESULT>
			>,

	ISharedProcessResult_OrderBy_Entity_Named<MODEL, RESULT>,			
	ISharedCompileEndClause<MODEL> {

}
