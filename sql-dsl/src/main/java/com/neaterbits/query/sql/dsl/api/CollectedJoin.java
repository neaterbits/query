package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

abstract class CollectedJoin extends CollectedItem {

	private final EJoinType joinType;
	private final Class<?> leftType;
	private final Class<?> rightType;
	private final List<CollectedJoinCondition> conditions;

	CollectedJoin(EJoinType joinType, Class<?> leftType, Class<?> rightType) {
		
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
	}


	final void addJoinCondition(CollectedJoinCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}
		
		this.conditions.add(condition);
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
