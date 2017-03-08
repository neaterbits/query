package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_In extends CollectedCondition_Value {

	CollectedCondition_In(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onIn(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.IN;
	}
}
