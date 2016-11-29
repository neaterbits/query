package com.neaterbits.query.sql.dsl.api;


abstract class ValueConditionImpl extends ConditionImpl {

	private final ConditionValueImpl value;
	
	ValueConditionImpl(Getter getter, ConditionValueImpl value) {
		super(getter);

		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}

		this.value = value;
	}

	final ConditionValueImpl getValue() {
		return value;
	}
}
