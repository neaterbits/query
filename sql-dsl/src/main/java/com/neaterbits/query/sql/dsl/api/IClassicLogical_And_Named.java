package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_And_Named<MODEL, RESULT>
		extends ISharedLogical_And_Named_All<MODEL, RESULT, IClassicLogical_And_Named<MODEL, RESULT>, IClassicLogical_Or_Named<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL> {

}
