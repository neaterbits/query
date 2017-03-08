package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_LessThan extends CollectedCondition_Comparison {
	
	CollectedCondition_LessThan(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onLessThan(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.LESS_THAN;
	}
}
