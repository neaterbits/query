package com.neaterbits.query.sql.dsl.api;

final class ConditionGreaterThanImpl extends ConditionComparisonImpl {

	ConditionGreaterThanImpl(Getter getter, ConditionValueImpl value) {
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
