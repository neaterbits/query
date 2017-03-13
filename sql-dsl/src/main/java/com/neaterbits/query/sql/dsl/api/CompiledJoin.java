package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledJoin {

	private final CollectedJoin original;
	private final TypeMapSource left;
	private final TypeMapSource right;
	
	// May have nested sub-joins that should be output as nested within the query
	
	private final List<CompiledJoinCondition> conditions;
	private final CompiledJoins subJoins;

	CompiledJoin(
			CollectedJoin original,
			TypeMapSource left,
			TypeMapSource right,
			List<CompiledJoinCondition> conditions,
			CompiledJoins subJoins) {

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

		this.subJoins = subJoins;
	}

	EJoinType getJoinType() {
		return original.getJoinType();
	}
	
	TypeMapSource getLeft() {
		return left;
	}

	TypeMapSource getRight() {
		return right;
	}
	
	List<CompiledJoinCondition> getConditions() {
		return conditions;
	}

	CompiledJoins getSubJoins() {
		return subJoins;
	}
}
