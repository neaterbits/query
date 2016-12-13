package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledJoin {

	private final CollectedJoin original;
	private final TypeMapSource left;
	private final TypeMapSource right;
	
	
	private final List<CompiledJoinCondition> conditions;

	CompiledJoin(
			CollectedJoin original,
			TypeMapSource left,
			TypeMapSource right,
			List<CompiledJoinCondition> conditions) {

		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (left == null) {
			throw new IllegalArgumentException("left == null");
		}
		
		if (right == null) {
			throw new IllegalArgumentException("right == null");
		}
		
		if (conditions == null) {
			throw new IllegalArgumentException("conditions == null");
		}
		
		if (conditions.isEmpty()) {
			throw new IllegalArgumentException("no conditions");
		}
		
		this.original = original;
		this.left = left;
		this.right = right;
		this.conditions = conditions;
	}

	JoinType getJoinType() {
		return original.getJoinType();
	}
	
	TypeMapSource getLeft() {
		return left;
	}

	TypeMapSource getRight() {
		return right;
	}
	
	final List<CompiledJoinCondition> getConditions() {
		return conditions;
	}
}
