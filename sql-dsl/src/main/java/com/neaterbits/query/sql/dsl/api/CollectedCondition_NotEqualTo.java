package com.neaterbits.query.sql.dsl.api;


final class CollectedCondition_NotEqualTo extends CollectedCondition_Value {

	CollectedCondition_NotEqualTo(Getter getter, ConditionValue value) {
		super(getter, value);
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
