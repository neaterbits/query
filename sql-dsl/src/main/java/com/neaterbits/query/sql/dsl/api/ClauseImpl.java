package com.neaterbits.query.sql.dsl.api;

final class ClauseImpl extends QueryBuilderItem {

	private final ClausesImpl<?, ?> clause;
	private final ConditionImpl condition;
	
	ClauseImpl(ClausesImpl<?, ?> clause, ConditionImpl condition) {
		
		if (clause == null) {
			throw new IllegalArgumentException("clause == null");
		}
		
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}
		
		this.clause = clause;
		this.condition = condition;
	}

	ClausesImpl<?, ?> getClause() {
		return clause;
	}

	ConditionImpl getCondition() {
		return condition;
	}
}
