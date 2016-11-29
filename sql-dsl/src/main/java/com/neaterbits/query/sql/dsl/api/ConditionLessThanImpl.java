package com.neaterbits.query.sql.dsl.api;

final class ConditionLessThanImpl extends ConditionComparisonImpl {
	
	ConditionLessThanImpl(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onLessThan(this, param);
	}
}
