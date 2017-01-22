package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndClausesAlias<MODEL, RESULT>
			extends ISharedAndClausesAlias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT>>,
					ISharedCompileEndClause<MODEL>{

}
