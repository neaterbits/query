package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_StringStartsWith extends CollectedCondition_String {

	CollectedCondition_StringStartsWith(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onStartsWith(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.STARTS_WITH;
	}
}
