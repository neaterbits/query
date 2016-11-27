package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

abstract class ConditionStringImpl extends ValueConditionImpl {

	ConditionStringImpl(Function<?, ?> getter, ConditionValueImpl value) {
		super(getter, value);
	}
}
