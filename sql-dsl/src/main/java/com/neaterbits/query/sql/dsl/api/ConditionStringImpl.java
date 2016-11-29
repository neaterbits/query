package com.neaterbits.query.sql.dsl.api;

abstract class ConditionStringImpl extends ValueConditionImpl {

	ConditionStringImpl(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}
}
