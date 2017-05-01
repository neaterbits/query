package com.neaterbits.query.sql.dsl.api;

enum EClauseOperator {

	IS_NULL(false),
	IS_NOT_NULL(false),
	
	IS_EQUAL(true),
	NOT_EQUAL(true),
	
	GREATER_THAN(true),
	GREATER_OR_EQUAL(true),
	
	LESS_THAN(true),
	LESS_OR_EQUAL(true),
	
	IN(true),
	
	STARTS_WITH(true),
	ENDS_WITH(true),
	CONTAINS(true),
	MATCHES(true);

	private final boolean hasConditionValue;

	private EClauseOperator(boolean hasParam) {
		this.hasConditionValue = hasParam;
	}

	public boolean hasConditionValue() {
		return hasConditionValue;
	}
}
