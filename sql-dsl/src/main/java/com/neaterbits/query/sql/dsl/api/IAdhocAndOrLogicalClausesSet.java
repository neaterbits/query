package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

public interface IAdhocAndOrLogicalClausesSet<MODEL, R, RESULT extends Set<R>>
		extends IAdhocAndOrLogicalClauses<MODEL, RESULT, R>,
				IAdhocEndClauseSet<MODEL, R, RESULT> {
	
}