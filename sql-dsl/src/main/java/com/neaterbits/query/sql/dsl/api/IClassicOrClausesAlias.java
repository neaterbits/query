package com.neaterbits.query.sql.dsl.api;

public interface IClassicOrClausesAlias<MODEL, RESULT>

	extends ISharedOrClausesAlias<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>, IClassicAndClausesAlias<MODEL, RESULT>>,
			ISharedCompileEndClause<MODEL> {

}
