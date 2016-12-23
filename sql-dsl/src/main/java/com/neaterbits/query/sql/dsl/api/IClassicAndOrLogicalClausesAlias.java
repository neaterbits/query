package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndOrLogicalClausesAlias<MODEL, RESULT>
		extends
			ISharedAndOrLogicalClausesAlias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT>>,
			ISharedCompileEndClause<MODEL>{

}
