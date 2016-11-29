package com.neaterbits.query.sql.dsl.api;

final class ConditionLessThanOrEqualImpl extends ConditionComparisonImpl {

	ConditionLessThanOrEqualImpl(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onLessThanOrEqual(this, param);
	}
}
