package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class ConditionValueFunctionImpl extends ConditionValueImpl {

	private final Function<?, ?> getter;

	ConditionValueFunctionImpl(Function<?, ?> getter) {

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}

	Function<?, ?> getGetter() {
		return getter;
	}
}
