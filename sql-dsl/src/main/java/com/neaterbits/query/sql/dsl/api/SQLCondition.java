package com.neaterbits.query.sql.dsl.api;

abstract class SQLCondition extends PreparedQueryComparisonRHS {

	private final String conditionResolvedPrefix;

	SQLCondition(String conditionResolvedPrefix) {
		
		if (conditionResolvedPrefix == null) {
			throw new IllegalArgumentException("conditionResolvedPrefix == null");
		}

		this.conditionResolvedPrefix = conditionResolvedPrefix;
	}

	final String getConditionResolvedPrefix() {
		return conditionResolvedPrefix;
	}
}

