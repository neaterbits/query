package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class ConditionStringStartsWith extends ConditionStringImpl {

	ConditionStringStartsWith(Function<?, ?> getter, ConditionValueLiteralStringImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onStartsWith(this, param);
	}
}
