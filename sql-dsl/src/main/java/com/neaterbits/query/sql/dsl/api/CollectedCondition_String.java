package com.neaterbits.query.sql.dsl.api;

abstract class CollectedCondition_String extends CollectedCondition_Value {

	CollectedCondition_String(Getter getter, ConditionValue value) {
		super(getter, value);
	}
}
