package com.neaterbits.query.sql.dsl.api;

final class QueryParamAndValue {
	private final BaseParamImpl<?> param;
	private final Object value;
	
	QueryParamAndValue(BaseParamImpl<?> param, Object value) {
		this.param = param;
		this.value = value;
	}

	BaseParamImpl<?> getParam() {
		return param;
	}

	Object getValue() {
		return value;
	}
}
