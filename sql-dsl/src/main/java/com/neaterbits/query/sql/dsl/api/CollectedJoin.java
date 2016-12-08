package com.neaterbits.query.sql.dsl.api;

abstract class CollectedJoin extends QueryBuilderItem {

	private final JoinType joinType;
	private final Class<?> leftType;
	private final Getter leftGetter;
	private final Class<?> rightType;
	private final Getter rightGetter;

	CollectedJoin(JoinType joinType, Class<?> leftType, Getter leftGetter, Class<?> rightType, Getter rightGetter) {
		
		if (joinType == null) {
			throw new IllegalArgumentException("joinType == null");
		}

		if (leftType == null) {
			throw new IllegalArgumentException("leftType == null");
		}
		
		if (leftGetter == null) {
			throw new IllegalArgumentException("leftGetter == null");
		}

		if (rightType == null) {
			throw new IllegalArgumentException("rightType == null");
		}
		
		if (rightGetter == null) {
			throw new IllegalArgumentException("rightGetter == null");
		}
		
		this.joinType = joinType;
		this.leftType = leftType;
		this.leftGetter = leftGetter;
		this.rightType = rightType;
		this.rightGetter = rightGetter;
	}

	final JoinType getJoinType() {
		return joinType;
	}

	final Class<?> getLeftType() {
		return leftType;
	}
	
	final Getter getLeftGetter() {
		return leftGetter;
	}

	final Class<?> getRightType() {
		return rightType;
	}

	final Getter getRightGetter() {
		return rightGetter;
	}
}
