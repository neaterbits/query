package com.neaterbits.query.sql.dsl.api;

abstract class CollectedCondition_String extends CollectedCondition_Value {

	CollectedCondition_String(Expression lhs, ConditionValue value) {
		super(lhs, value);
	}
}
