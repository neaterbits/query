package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_IsNotNull extends CollectedCondition_Basic {

	CollectedCondition_IsNotNull(Expression lhs) {
		super(lhs);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onIsNotNull(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.IS_NOT_NULL;
	}
}
