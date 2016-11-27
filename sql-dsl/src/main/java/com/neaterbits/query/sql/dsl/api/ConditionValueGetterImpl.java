package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class ConditionValueGetterImpl extends ConditionValueImpl {

	private final Function<?, ?> getter;

	ConditionValueGetterImpl(Function<?, ?> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}
}
