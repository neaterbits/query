package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects clauses in a query
 *
 */

final class ClauseCollectorImpl {
	
	private final ClausesImpl<?, ?> clausesImpl;
	private final List<ConditionImpl> conditions;

	ClauseCollectorImpl(ClausesImpl<?, ?> clausesImpl) {
		
		if (clausesImpl == null) {
			throw new IllegalArgumentException("clausesImpl == null");
		}

		this.clausesImpl = clausesImpl;
		this.conditions = new ArrayList<ConditionImpl>();
	}

	ClausesImpl<?, ?> getClausesImpl() {
		return clausesImpl;
	}

	
	void add(ConditionImpl condition) {
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		conditions.add(condition);
	}
}
