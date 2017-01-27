package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndOrLogicalClausesAlias<MODEL, RESULT>
		extends
			ISharedLogical_And_Or_Alias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT>>,
			ISharedCompileEndClause<MODEL>{

}
