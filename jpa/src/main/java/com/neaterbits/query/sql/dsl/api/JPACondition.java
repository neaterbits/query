package com.neaterbits.query.sql.dsl.api;

abstract class JPACondition extends PreparedQueryComparisonRHS {

	private final String conditionResolvedPrefix;

	JPACondition(String conditionResolvedPrefix) {
		
		if (conditionResolvedPrefix == null) {
			throw new IllegalArgumentException("conditionResolvedPrefix == null");
		}

		this.conditionResolvedPrefix = conditionResolvedPrefix;
	}

	final String getConditionResolvedPrefix() {
		return conditionResolvedPrefix;
	}
}

