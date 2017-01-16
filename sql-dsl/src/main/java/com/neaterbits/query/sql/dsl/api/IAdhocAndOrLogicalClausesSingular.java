package com.neaterbits.query.sql.dsl.api;

public interface IAdhocAndOrLogicalClausesSingular<MODEL, RESULT, ENTITY>
	extends IAdhocAndOrLogicalClauses<MODEL, RESULT, ENTITY>,
			IAdhocEndClauseSingular<MODEL, RESULT> {
}
