package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_LessThanOrEqual extends CollectedCondition_Comparison {

	CollectedCondition_LessThanOrEqual(Getter getter, ConditionValue value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onLessThanOrEqual(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.LESS_OR_EQUAL;
	}
}
