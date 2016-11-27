package com.neaterbits.query.sql.dsl.api;

final class QueryParamAndValue {
	private final ParamImpl<?> param;
	private final Object value;
	
	QueryParamAndValue(ParamImpl<?> param, Object value) {
		this.param = param;
		this.value = value;
	}

	ParamImpl<?> getParam() {
		return param;
	}

	Object getValue() {
		return value;
	}
}
