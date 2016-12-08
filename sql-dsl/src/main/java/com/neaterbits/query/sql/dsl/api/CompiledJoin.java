package com.neaterbits.query.sql.dsl.api;

final class CompiledJoin {

	private final CollectedJoin original;
	private final TypeMapSource left;
	private final TypeMapSource right;
	
	CompiledJoin(CollectedJoin original, TypeMapSource left, TypeMapSource right) {
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

	CollectedJoin getOriginal() {
		return original;
	}

	TypeMapSource getLeft() {
		return left;
	}

	TypeMapSource getRight() {
		return right;
	}
}
