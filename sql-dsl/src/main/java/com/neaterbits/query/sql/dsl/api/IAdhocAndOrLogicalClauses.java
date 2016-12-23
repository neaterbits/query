package com.neaterbits.query.sql.dsl.api;

public interface IAdhocAndOrLogicalClauses<MODEL, RESULT> 
		extends ISharedAndOrLogicalClausesTable<
						MODEL, 
						RESULT,
						IAdhocAndClauses<MODEL, RESULT>,
						IAdhocOrClauses<MODEL, RESULT>>,
						
			IAdhocGetEndClause<MODEL, RESULT> {

}
