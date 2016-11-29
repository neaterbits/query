package com.neaterbits.query.sql.dsl.api;

abstract class ConditionComparisonImpl extends ValueConditionImpl {

	ConditionComparisonImpl(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}
}

