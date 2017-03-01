package com.neaterbits.query.sql.dsl.api;

abstract class SQLConditionUnresolved extends SQLCondition {

	SQLConditionUnresolved(String conditionResolvedPrefix) {
		super(conditionResolvedPrefix);
	}

	@Override
	final boolean isUnresolved() {
		return true;
	}
}
