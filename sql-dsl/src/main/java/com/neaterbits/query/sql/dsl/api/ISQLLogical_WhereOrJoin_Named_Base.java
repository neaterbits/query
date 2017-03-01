package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT> 
		extends 
			ISQLJoin_Named_Base<MODEL,RESULT>,
			ISharedCompileEndClause<MODEL>,
			ISharedLogical_Where<MODEL, RESULT> {

}
