package com.neaterbits.query.sql.dsl.api;


public interface IClassicSingleOrClausesNamed<MODEL, RESULT>
		extends ISharedSingleOrClausesNamed<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>>,
			ISharedCompileEndClause<MODEL> {

	
}
