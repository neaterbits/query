package com.neaterbits.query.sql.dsl.api;


public interface IClassicSingleAndClausesNamed<MODEL, RESULT>
		extends ISharedSingleAndClausesNamed<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL>{
	
}
