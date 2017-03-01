package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_AndOr_MultiMapped_Named<MODEL, RESULT> 
	extends
	ISharedLogical_And_Or_Named_All<
			MODEL, 
			RESULT,
	
			ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>,

	
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>
			>,
	
	ISharedProcessResult_All_Named<MODEL, RESULT>,			
	ISharedCompileEndClause<MODEL> {

}
