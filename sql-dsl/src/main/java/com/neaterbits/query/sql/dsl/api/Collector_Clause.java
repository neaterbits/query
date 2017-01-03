package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects clauses in a query
 *
 */

final class Collector_Clause {
	
	private final List<CollectedClause> clauses;

	Collector_Clause() {
		this.clauses = new ArrayList<CollectedClause>();
	}
		
	
	void add(CollectedClauses<?, ?> clause, CollectedCondition condition) {
		
		if (clause == null) {
			throw new IllegalArgumentException("clause == null");
		}

		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		clauses.add(new CollectedClause(clause, condition));
	}


	List<CollectedClause> getClauses() {
		return clauses;
	}
}
