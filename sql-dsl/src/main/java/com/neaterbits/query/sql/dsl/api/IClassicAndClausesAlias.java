package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndClausesAlias<MODEL, RESULT>
			extends ISharedLogical_And_Alias_Base<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT>>,
					ISharedCompileEndClause<MODEL>{

}
