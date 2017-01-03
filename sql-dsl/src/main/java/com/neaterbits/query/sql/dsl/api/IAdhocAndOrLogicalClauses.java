package com.neaterbits.query.sql.dsl.api;

public interface IAdhocAndOrLogicalClauses<MODEL, RESULT> 
		extends ISharedAndOrLogicalClausesNamedValues<
						MODEL, 
						RESULT,
						IAdhocAndClauses<MODEL, RESULT>,
						IAdhocOrClauses<MODEL, RESULT>> {

}
