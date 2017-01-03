package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_GreaterThanOrEqual extends CollectedCondition_Comparison  {

	CollectedCondition_GreaterThanOrEqual(Getter getter, ConditionValue value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onGreaterThanOrEqual(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.GREATER_OR_EQUAL;
	}
}
