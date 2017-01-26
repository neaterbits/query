package com.neaterbits.query.sql.dsl.api;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.neaterbits.query.util.java8.Coll8;

abstract class PreparedQueryConditionsBuilder {

	abstract void addJoinCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right);

	abstract void resolveFromParams(StringBuilder sb, ParamValueResolver resolver);
	
	abstract PreparedQueryConditionsBuilder createConditionsBuilder(boolean atRoot);
	

	private final boolean atRoot;
	private ConditionsType joinType;
	private ConditionsType comparisonType;
	private List<PreparedQueryCondition> conditions;
	
	PreparedQueryConditionsBuilder(boolean atRoot) {
		this.atRoot = atRoot;
		this.joinType = null;
		this.comparisonType = null;
		this.conditions = new ArrayList<>();
	}
	
	final PreparedQueryConditionsBuilder addNestedForJoin(ConditionsType type) {

		updateJoinType(type);
		
		return addNested(type);
	}
		
	final PreparedQueryConditionsBuilder addNestedForRegularSub(ConditionsType type) {

		return addNested(type);
	}
				
	private PreparedQueryConditionsBuilder addNested(ConditionsType type) { 		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		final PreparedQueryConditionsBuilder sub = createConditionsBuilder(false);

		conditions.add(new PreparedQueryConditionNested(sub));
		
		return sub;
	}

	final boolean hasUnresolved() {
		return Coll8.has(conditions, condition -> condition.isUnresolved());
	}

	final void addComparisonCondition(ConditionsType type, PreparedQueryConditionComparison condition) {
		
		updateComparisonType(type);
		
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		conditions.add(condition);
	}

	final boolean isEmpty() {
		return conditions.isEmpty();
	}

	final int size() {
		return conditions.size();
	}

	final ConditionsType getType() {
		return joinType != null ? joinType : comparisonType;
	}
	
	final boolean isAtRoot() {
		return atRoot;
	}
	
	final void walk(Consumer<PreparedQueryConditionComparison> consumer) {

		for (PreparedQueryCondition condition : conditions) {
			condition.walk(consumer);
		}
	}
	

	Iterable<PreparedQueryCondition> getConditions() {
		return conditions;
	}
	
	final ConditionsType updateJoinType(ConditionsType type) {
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		final ConditionsType lastJoinType = this.joinType;
		
		if (this.joinType == null) {
			if (type != ConditionsType.AND) {
				throw new IllegalStateException("Expected AND for joins, got " + type);
			}
			
			this.joinType = type;
		}
		else {
			if (type != ConditionsType.AND) {
				throw new IllegalStateException("Expected AND for joins, got " + type);
			}
			
			if (this.joinType != ConditionsType.AND) {
				throw new IllegalStateException("Expected already AND for joins");
			}
		}

		return lastJoinType;
	}
	
	private void updateComparisonType(ConditionsType type) {
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (this.comparisonType == null) {
			if (type != ConditionsType.SINGLE && type != ConditionsType.AND && type != ConditionsType.OR) {
				throw new IllegalStateException("Expected SINGLE, AND or OR as first: " + type);
			}
			
			this.comparisonType = type;
		}
		else {
			if (type != ConditionsType.AND && type != ConditionsType.OR) {
				throw new IllegalStateException("Expected AND or OR for comparison");
			}
			
			if (joinType != null && type != joinType) {
				throw new IllegalStateException("Mismatch with join type");
			}
			
			// Check that does not change eg from AND to OR
			if (this.comparisonType != ConditionsType.SINGLE) {
				if (type != this.comparisonType) {
					throw new IllegalArgumentException("Changed in comparison type");
				}
			}
			
			this.comparisonType = type;
		}
	}
	
	
}
