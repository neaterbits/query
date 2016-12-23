package com.neaterbits.query.sql.dsl.api;


public interface IClassicSingleAndClausesTable<MODEL, RESULT>
		extends ISharedSingleAndClausesTable<MODEL, RESULT, IClassicSingleAndClausesTable<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL>{
	
}
