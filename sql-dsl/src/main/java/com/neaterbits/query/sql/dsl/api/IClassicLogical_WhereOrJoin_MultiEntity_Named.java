package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT>
	extends IClassicLogical_Where_MultiEntity_Named<MODEL, RESULT>,
			IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			IClassicJoin_MultiEntity_Named<MODEL, RESULT>,
			IClassicLogical_AndOr_MultiEntity_Named<MODEL, RESULT>,
			ISharedProcessResult_OrderBy_Entity_Named<MODEL, RESULT> { 
	
}
