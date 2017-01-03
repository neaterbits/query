package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_LessThan extends CollectedCondition_Comparison {
	
	CollectedCondition_LessThan(Getter getter, ConditionValue value) {
		super(getter, value);
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
