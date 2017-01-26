package com.neaterbits.query.sql.dsl.api;

abstract class BaseParamImpl<T> {
	private final Class<T> paramType;

	abstract boolean check(Object value);
	
	abstract boolean isList();
	
	BaseParamImpl(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		this.paramType = paramType;
	}

	final Class<T> getParamType() {
		return paramType;
	}

}
