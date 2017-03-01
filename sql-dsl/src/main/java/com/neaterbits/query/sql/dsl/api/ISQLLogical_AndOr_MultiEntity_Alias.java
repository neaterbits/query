package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>
		extends
		ISharedLogical_And_Or_Alias<
				MODEL, 
				RESULT,
				ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
				ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
				
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias <MODEL, RESULT>>,
		
		ISharedProcessResult_OrderBy_Entity_Alias<MODEL, RESULT>,				
		ISharedCompileEndClause<MODEL>
{

}
