package com.neaterbits.query.sql.dsl.api;


final class ConditionValueGetterImpl extends ConditionValueImpl {

	private final Getter getter;

	ConditionValueGetterImpl(Getter getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}
}
