package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_GreaterThanOrEqual extends CollectedCondition_Comparison  {

	CollectedCondition_GreaterThanOrEqual(Expression lhs, ConditionValue value) {
		super(lhs, value);
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
