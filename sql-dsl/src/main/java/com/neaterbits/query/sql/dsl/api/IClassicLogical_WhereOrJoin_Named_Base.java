package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT> 
		extends 
			IClassicJoin_Named_Base<MODEL,RESULT>,
			ISharedCompileEndClause<MODEL>,
			ISharedLogical_Where<MODEL, RESULT> {

}
