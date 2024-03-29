package com.neaterbits.query.sql.dsl.api;


abstract class CollectedCondition_Value extends CollectedCondition_Basic {

	private final ConditionValue value;
	
	CollectedCondition_Value(Expression lhs, ConditionValue value) {
		super(lhs);

		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}

		this.value = value;
	}
	
	/*
	@Deprecated
	private CollectedCondition_Value(Getter getter, ConditionValue value) {
		super(getter);

		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}

		this.value = value;
	}
	*/

	final ConditionValue getValue() {
		return value;
	}
}
