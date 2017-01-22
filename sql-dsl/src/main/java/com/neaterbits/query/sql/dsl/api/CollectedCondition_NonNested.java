package com.neaterbits.query.sql.dsl.api;

abstract class CollectedCondition_NonNested extends CollectedCondition {

	private final Getter getter;

	abstract EClauseOperator getOperator();

	CollectedCondition_NonNested(Getter getter) {

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}

	final Getter getGetter() {
		return getter;
	}
}
