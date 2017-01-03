package com.neaterbits.query.sql.dsl.api;

abstract class CollectedCondition_Comparison extends CollectedCondition_Value {

	CollectedCondition_Comparison(Getter getter, ConditionValue value) {
		super(getter, value);
	}
}

