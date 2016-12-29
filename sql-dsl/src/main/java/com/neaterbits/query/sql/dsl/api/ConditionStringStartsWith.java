package com.neaterbits.query.sql.dsl.api;

final class ConditionStringStartsWith extends ConditionStringImpl {

	ConditionStringStartsWith(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onStartsWith(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.STARTS_WITH;
	}
}
