package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class CollectedJoin extends CollectedItem {

	private final EJoinType joinType;
	private final Class<?> leftType;
	private final Class<?> rightType;
	private final List<CollectedJoinCondition> conditions;
	private final Collector_Joins subJoins;

	CollectedJoin(EJoinType joinType, Class<?> leftType, Class<?> rightType, Collector_Joins subJoins) {
		
		if (joinType == null) {
			throw new IllegalArgumentException("joinType == null");
		}

		if (leftType == null) {
			throw new IllegalArgumentException("leftType == null");
		}

		if (rightType == null) {
			throw new IllegalArgumentException("rightType == null");
		}
		
		this.joinType = joinType;
		this.leftType = leftType;
		this.rightType = rightType;
		this.conditions = new ArrayList<>();
		// may be null
		this.subJoins = subJoins;
	}

	CollectedJoin(EJoinType joinType, CollectedJoinCondition condition, Collector_Joins subJoins) {

		if (joinType == null) {
			throw new IllegalArgumentException("joinType == null");
		}

		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}
		
		this.joinType = joinType;
		this.leftType = null;
		this.rightType = null;
		this.conditions = Arrays.asList(condition);
		// may be null
		this.subJoins = subJoins;
	}

	final void addJoinCondition(CollectedJoinCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		this.conditions.add(condition);
	}

	final Collector_Joins getSubJoins() {
		return subJoins;
	}
	
	final List<CollectedJoinCondition> getJoinConditions() {
		return conditions;
	}

	final EJoinType getJoinType() {
		return joinType;
	}

	final Class<?> getLeftType() {
		return leftType;
	}
	
	final Class<?> getRightType() {
		return rightType;
	}
}
