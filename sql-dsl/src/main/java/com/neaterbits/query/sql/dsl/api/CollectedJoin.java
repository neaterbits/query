package com.neaterbits.query.sql.dsl.api;

abstract class CollectedJoin extends QueryBuilderItem {

	private final JoinType joinType;
	private final Class<?> leftType;
	private final Class<?> rightType;

	CollectedJoin(JoinType joinType, Class<?> leftType, Class<?> rightType) {
		
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
	}

	final JoinType getJoinType() {
		return joinType;
	}
	
}
