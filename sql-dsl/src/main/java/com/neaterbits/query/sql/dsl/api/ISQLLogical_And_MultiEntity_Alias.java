package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>
			extends ISharedLogical_And_Alias_Base<
				MODEL,
				RESULT,
				ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
			
			ISharedCompileEndClause<MODEL> {



}
