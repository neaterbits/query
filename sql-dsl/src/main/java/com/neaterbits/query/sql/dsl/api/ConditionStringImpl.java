package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

abstract class ConditionStringImpl extends ValueConditionImpl {

	ConditionStringImpl(Function<?, ?> getter, ConditionValueLiteralStringImpl value) {
		super(getter, value);
	}
}
