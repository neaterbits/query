package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class ConditionStringMatches extends ConditionStringImpl {

	ConditionStringMatches(Function<?, ?> getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onMatches(this, param);
	}
}
