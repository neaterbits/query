package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_StringMatches extends CollectedCondition_String {

	CollectedCondition_StringMatches(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onMatches(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.MATCHES;
	}
}
