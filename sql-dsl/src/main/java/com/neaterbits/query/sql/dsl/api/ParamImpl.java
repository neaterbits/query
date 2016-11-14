package com.neaterbits.query.sql.dsl.api;

final class ParamImpl<T> implements Param<T> {
	private final Class<T> paramType;

	ParamImpl(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		this.paramType = paramType;
	}

	Class<T> getParamType() {
		return paramType;
	}
}
