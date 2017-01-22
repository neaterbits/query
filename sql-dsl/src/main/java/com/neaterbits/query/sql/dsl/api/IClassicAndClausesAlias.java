package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndClausesAlias<MODEL, RESULT>
			extends ISharedAndClausesAliasBase<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT>>,
					ISharedCompileEndClause<MODEL>{

}
