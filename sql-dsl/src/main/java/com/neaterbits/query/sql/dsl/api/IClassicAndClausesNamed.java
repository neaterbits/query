package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndClausesNamed<MODEL, RESULT>
		extends ISharedAndClausesNamedAll<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>, IClassicOrClausesNamed<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL> {

}
