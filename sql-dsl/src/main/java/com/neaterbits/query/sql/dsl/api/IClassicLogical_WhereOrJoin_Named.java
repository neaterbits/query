package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_WhereOrJoin_Named<MODEL, RESULT>
	extends IClassicLogical_Where_Named<MODEL, RESULT>,
			IClassicLogical_Join_Named<MODEL,RESULT>,
			ISharedCompileEndClause<MODEL> {

}
