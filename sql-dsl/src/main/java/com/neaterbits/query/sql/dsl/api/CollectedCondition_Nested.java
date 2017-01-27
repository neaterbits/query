package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_Nested extends CollectedCondition {
	
	private final Collector_Conditions<?, ?, ?, ?> collected;

	CollectedCondition_Nested(Collector_Conditions<?, ?, ?, ?> collected) {

		if (collected == null) {
			throw new IllegalArgumentException("collected == null");
		}

		this.collected = collected;
	}

	Collector_Conditions<?, ?, ?, ?> getCollected() {
		return collected;
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onNested(this, param);
	}
}
