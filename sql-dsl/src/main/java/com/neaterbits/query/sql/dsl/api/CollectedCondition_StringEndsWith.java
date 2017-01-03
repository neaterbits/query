package com.neaterbits.query.sql.dsl.api;

final class CollectedCondition_StringEndsWith extends CollectedCondition_String {

	CollectedCondition_StringEndsWith(Getter getter, ConditionValue value) {
		super(getter, value);
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
