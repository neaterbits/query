package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

abstract class ConditionComparisonImpl extends ValueConditionImpl {

	ConditionComparisonImpl(Function<?, ?> getter, ConditionValueImpl value) {
		super(getter, value);
	}
}

