package com.neaterbits.query.sql.dsl.api;


final class CollectedCondition_NotEqualTo extends CollectedCondition_Value {

	CollectedCondition_NotEqualTo(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}

	@Override
	final <T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onNotEqualTo(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.NOT_EQUAL;
	}
}
