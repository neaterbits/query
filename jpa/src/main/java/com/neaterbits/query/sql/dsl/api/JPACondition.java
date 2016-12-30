package com.neaterbits.query.sql.dsl.api;

abstract class JPACondition extends PreparedQueryConditionRHS {

	private final String prefix;

	JPACondition(String prefix) {
		
		if (prefix == null) {
			throw new IllegalArgumentException("prefix == null");
		}

		this.prefix = prefix;
	}

	final String getPrefix() {
		return prefix;
	}
}

