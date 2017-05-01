package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_IsNull extends CollectedCondition_Basic {

	CollectedCondition_IsNull(Expression lhs) {
		super(lhs);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onIsNull(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.IS_NULL;
	}
}
