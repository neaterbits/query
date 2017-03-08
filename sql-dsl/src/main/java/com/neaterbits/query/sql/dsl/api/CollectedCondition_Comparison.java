package com.neaterbits.query.sql.dsl.api;

abstract class CollectedCondition_Comparison extends CollectedCondition_Value {

	CollectedCondition_Comparison(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}
}

