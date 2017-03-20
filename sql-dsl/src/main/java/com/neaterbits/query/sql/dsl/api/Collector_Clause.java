package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects clauses in a query
 *
 */

final class Collector_Clause {
	
    private final EConditionsClause conditionsClause;		
	private final ConditionsType conditionsType;
	private final List<CollectedCondition> conditions;

	Collector_Clause(Collector_Clause whereClauses, ConditionsType newConditionsType) {
		
		
		if (newConditionsType != ConditionsType.AND && newConditionsType != ConditionsType.OR) {
			throw new IllegalArgumentException("expected AND or OR conditions type: " + newConditionsType);
		}
		
		if (whereClauses.conditionsType != ConditionsType.SINGLE) {
			throw new IllegalArgumentException("Expected where clause");
		}
		
		if (whereClauses.conditions.size() != 1) {
			throw new IllegalArgumentException("expected exactly one condition collected, got " + whereClauses.conditions.size());
		}

		this.conditionsClause = whereClauses.conditionsClause;
		this.conditionsType = newConditionsType;
		this.conditions = new ArrayList<>(whereClauses.conditions); // keep condition from WHERE clause
	}

	Collector_Clause(EConditionsClause conditionsClause, ConditionsType conditionsType) {
		
		if (conditionsClause == null) {
			throw new IllegalArgumentException("conditionsClause == null");
		}

		if (conditionsType == null) {
			throw new IllegalArgumentException("conditionsType == null");
		}

		this.conditionsClause = conditionsClause;
		this.conditionsType = conditionsType;
		this.conditions = new ArrayList<CollectedCondition>();
	}
	
	
	boolean isEmpty() {
		return conditions.isEmpty();
	}
	
	EConditionsClause getConditionsClause() {
		return conditionsClause;
	}

	ConditionsType getConditionsType() {
		return conditionsType;
	}


	List<CollectedCondition> getConditions() {
		return conditions;
	}


	void add(CollectedCondition condition) {
		
		if (conditionsType == ConditionsType.SINGLE && !conditions.isEmpty()) {
			throw new IllegalStateException("Trying to add to SINGLE-condition, should create new one");
		}
		

		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		conditions.add(condition);
	}
}
