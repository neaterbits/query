package com.neaterbits.query.sql.dsl.api;


final class ConditionEqualToImpl extends ValueConditionImpl {

	public ConditionEqualToImpl(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onEqualTo(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.IS_EQUAL;
	}
}
