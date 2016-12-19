package com.neaterbits.query.sql.dsl.api;

abstract class CompiledJoinCondition {
	private final CollectedJoinCondition original;
	private final TypeMapSource left;
	private final TypeMapSource right;
	
	CompiledJoinCondition(CollectedJoinCondition original, TypeMapSource left, TypeMapSource right) {
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (left == null) {
			throw new IllegalArgumentException("left == null");
		}
		
		if (right == null) {
			throw new IllegalArgumentException("right == null");
		}
		
		this.original = original;
		this.left = left;
		this.right = right;
	}

	TypeMapSource getLeft() {
		return left;
	}

	TypeMapSource getRight() {
		return right;
	}

	boolean evaluate(Object instance1, Object instance2) {
		throw new UnsupportedOperationException("TODO");
	}
}
