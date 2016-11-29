package com.neaterbits.query.sql.dsl.api;

final class ConditionStringContains extends ConditionStringImpl {

	ConditionStringContains(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onContains(this, param);
	}
}
