package com.neaterbits.query.sql.dsl.api;

abstract class BaseConditionValueParamImpl {

	private final Param<?> param;

	BaseConditionValueParamImpl(Param<?> param) {

		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}

		this.param = param;
	}
}
