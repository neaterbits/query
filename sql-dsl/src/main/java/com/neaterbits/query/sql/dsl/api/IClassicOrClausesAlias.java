package com.neaterbits.query.sql.dsl.api;

public interface IClassicOrClausesAlias<MODEL, RESULT>

	extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>, IClassicAndClausesAlias<MODEL, RESULT>>,
			ISharedCompileEndClause<MODEL> {

}
