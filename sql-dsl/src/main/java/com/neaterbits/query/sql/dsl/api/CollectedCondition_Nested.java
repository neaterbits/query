package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_Nested extends CollectedCondition {
	
	private final Collector_Conditions_GroupBy<?, ?, ?> collected;

	CollectedCondition_Nested(Collector_Conditions_GroupBy<?, ?, ?> collected) {

		if (collected == null) {
			throw new IllegalArgumentException("collected == null");
		}

		this.collected = collected;
	}

	Collector_Conditions_GroupBy<?, ?, ?> getCollected() {
		return collected;
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onNested(this, param);
	}
}
