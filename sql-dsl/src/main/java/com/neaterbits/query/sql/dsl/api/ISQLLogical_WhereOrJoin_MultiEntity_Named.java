package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT>
	extends ISQLLogical_Where_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			ISQLJoin_MultiEntity_Named<MODEL, RESULT>,
			ISharedProcessResult_OrderBy_Entity_Named<MODEL, RESULT> { 
	
}
