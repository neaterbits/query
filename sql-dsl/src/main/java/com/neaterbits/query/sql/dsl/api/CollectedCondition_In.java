package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_In extends CollectedCondition_Value {

	CollectedCondition_In(Getter getter, ConditionValue value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onIn(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.IN;
	}
}
