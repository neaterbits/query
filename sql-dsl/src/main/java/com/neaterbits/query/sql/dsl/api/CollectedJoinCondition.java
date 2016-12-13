package com.neaterbits.query.sql.dsl.api;

abstract class CollectedJoinCondition extends QueryBuilderItem {
	private final Getter leftGetter;
	private final Getter rightGetter;

	CollectedJoinCondition(Getter leftGetter, Getter rightGetter) {
		
		
		if (leftGetter == null) {
			throw new IllegalArgumentException("leftGetter == null");
		}

		if (rightGetter == null) {
			throw new IllegalArgumentException("rightGetter == null");
		}
		
		this.leftGetter = leftGetter;
		this.rightGetter = rightGetter;
	}

	final Getter getLeftGetter() {
		return leftGetter;
	}

	final Getter getRightGetter() {
		return rightGetter;
	}
}
