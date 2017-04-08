package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>
		extends ISharedLogical_Or_Alias_Base<
			MODEL,
			RESULT,
			ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,
			ISharedCompileEndClause<MODEL>,
			
			ISharedProcessResult_OrderBy_Entity_Alias<MODEL, RESULT> {

}
