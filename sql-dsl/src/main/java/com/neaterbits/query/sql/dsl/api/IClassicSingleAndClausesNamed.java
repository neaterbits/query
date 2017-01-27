package com.neaterbits.query.sql.dsl.api;


public interface IClassicSingleAndClausesNamed<MODEL, RESULT>
		extends ISharedLogical_And_Single_Named<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL>{
	
}
