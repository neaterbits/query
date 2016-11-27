package com.neaterbits.query.sql.dsl.api;

final class ConditionValueParamImpl extends ConditionValueImpl {

	private final Param<?> param;

	ConditionValueParamImpl(Param<?> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}

		this.param = param;
	}
}
