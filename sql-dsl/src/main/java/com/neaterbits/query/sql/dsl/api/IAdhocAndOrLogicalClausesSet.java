package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

public interface IAdhocAndOrLogicalClausesSet<MODEL, T, RESULT extends Set<T>>
		extends IAdhocAndOrLogicalClauses<MODEL, RESULT>,
				IAdhocEndClauseSet<MODEL, T, RESULT> {
	
}