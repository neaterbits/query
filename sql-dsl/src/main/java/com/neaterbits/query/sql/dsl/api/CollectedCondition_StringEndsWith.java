package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_StringEndsWith extends CollectedCondition_String {

	CollectedCondition_StringEndsWith(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onEndsWith(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.ENDS_WITH;
	}
}
