package com.neaterbits.query.sql.dsl.api;

final class CollectedClause extends CollectedItem {

	private final CollectedClauses<?, ?> clause;
	private final CollectedCondition condition;
	
	CollectedClause(CollectedClauses<?, ?> clause, CollectedCondition condition) {
		
		if (clause == null) {
			throw new IllegalArgumentException("clause == null");
		}
		
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}
		
		this.clause = clause;
		this.condition = condition;
	}

	CollectedClauses<?, ?> getClause() {
		return clause;
	}

	CollectedCondition getCondition() {
		return condition;
	}
}
