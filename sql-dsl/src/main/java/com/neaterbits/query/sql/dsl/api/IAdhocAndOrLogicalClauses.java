package com.neaterbits.query.sql.dsl.api;

public interface IAdhocAndOrLogicalClauses<MODEL, RESULT> 
		extends ISharedAndOrLogicalClausesTableValues<
						MODEL, 
						RESULT,
						IAdhocAndClauses<MODEL, RESULT>,
						IAdhocOrClauses<MODEL, RESULT>> {

}
