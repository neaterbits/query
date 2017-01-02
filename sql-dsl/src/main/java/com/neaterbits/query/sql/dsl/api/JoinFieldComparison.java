package com.neaterbits.query.sql.dsl.api;

final class JoinFieldComparison {

	private final FieldReference left;
	private final FieldReference right;

	
	JoinFieldComparison(FieldReference left, FieldReference right) {
		
		if (left == null) {
			throw new IllegalArgumentException("left == null");
		}

		if (right == null) {
			throw new IllegalArgumentException("right == null");
		}
		
		this.left = left;
		this.right = right;
	}

	FieldReference getLeft() {
		return left;
	}

	FieldReference getRight() {
		return right;
	}
}
