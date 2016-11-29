package com.neaterbits.query.sql.dsl.api;

final class ConditionGreaterThanOrEqualImpl extends ConditionComparisonImpl  {

	ConditionGreaterThanOrEqualImpl(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onGreaterThanOrEqual(this, param);
	}
}
