package com.neaterbits.query.sql.dsl.api;


final class CollectedCondition_EqualTo extends CollectedCondition_Value {

	public CollectedCondition_EqualTo(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onEqualTo(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.IS_EQUAL;
	}
}
