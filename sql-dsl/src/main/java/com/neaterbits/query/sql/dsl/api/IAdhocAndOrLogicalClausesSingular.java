package com.neaterbits.query.sql.dsl.api;

public interface IAdhocAndOrLogicalClausesSingular<MODEL, RESULT>
	extends IAdhocAndOrLogicalClauses<MODEL, RESULT>,
			IAdhocEndClauseSingular<MODEL, RESULT> {
}
