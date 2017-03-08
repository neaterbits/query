package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_StringContains extends CollectedCondition_String {

	CollectedCondition_StringContains(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onContains(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.CONTAINS;
	}
}
