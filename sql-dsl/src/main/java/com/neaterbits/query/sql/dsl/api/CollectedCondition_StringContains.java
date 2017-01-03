package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_StringContains extends CollectedCondition_String {

	CollectedCondition_StringContains(Getter getter, ConditionValue value) {
		super(getter, value);
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
