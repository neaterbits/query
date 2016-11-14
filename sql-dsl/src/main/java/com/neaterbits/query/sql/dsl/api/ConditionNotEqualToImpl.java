package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class ConditionNotEqualToImpl extends ValueConditionImpl {

	ConditionNotEqualToImpl(Function<?, ?> getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	final <T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onNotEqualTo(this, param);
	}
}
