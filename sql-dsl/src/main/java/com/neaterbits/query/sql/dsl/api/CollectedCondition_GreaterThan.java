package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_GreaterThan extends CollectedCondition_Comparison {

	CollectedCondition_GreaterThan(Getter getter, ConditionValue value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onGreaterThan(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.GREATER_THAN;
	}
}
