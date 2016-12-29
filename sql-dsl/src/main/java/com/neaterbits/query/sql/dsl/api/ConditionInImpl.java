package com.neaterbits.query.sql.dsl.api;

final class ConditionInImpl extends ValueConditionImpl {

	ConditionInImpl(Getter getter, ConditionValueImpl value) {
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
