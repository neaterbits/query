package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_Or_Alias<MODEL, RESULT>

	extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, IClassicLogical_Or_Alias<MODEL, RESULT>, IClassicLogical_And_Alias<MODEL, RESULT>>,
			ISharedCompileEndClause<MODEL> {

}
