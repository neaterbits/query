package com.neaterbits.query.sql.dsl.api;

public interface IClassicOrClausesAlias<MODEL, RESULT>

	extends ISharedOrClausesAliasBase<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>, IClassicAndClausesAlias<MODEL, RESULT>>,
			ISharedCompileEndClause<MODEL> {

}
