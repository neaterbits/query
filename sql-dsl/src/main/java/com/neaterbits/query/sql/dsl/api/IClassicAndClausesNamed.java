package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndClausesNamed<MODEL, RESULT>
		extends ISharedLogical_And_Named_All<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>, IClassicOrClausesNamed<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL> {

}
