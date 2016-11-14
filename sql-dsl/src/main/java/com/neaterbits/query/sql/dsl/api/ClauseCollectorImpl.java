package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects clauses in a query
 *
 */

final class ClauseCollectorImpl {
	
	private final List<ClauseImpl> clauses;

	ClauseCollectorImpl() {
		this.clauses = new ArrayList<ClauseImpl>();
	}
		
	
	void add(ClausesImpl<?, ?> clause, ConditionImpl condition) {
		
		if (clause == null) {
			throw new IllegalArgumentException("clause == null");
		}

		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		clauses.add(new ClauseImpl(clause, condition));
	}
}
