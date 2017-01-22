package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_Nested extends CollectedCondition {
	
	private final CollectedClauses<?, ?> collected;

	CollectedCondition_Nested(CollectedClauses<?, ?> collected) {

		if (collected == null) {
			throw new IllegalArgumentException("collected == null");
		}

		this.collected = collected;
	}

	CollectedClauses<?, ?> getCollected() {
		return collected;
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onNested(this, param);
	}
}
