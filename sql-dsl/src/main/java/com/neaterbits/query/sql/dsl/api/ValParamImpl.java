package com.neaterbits.query.sql.dsl.api;

final class ValParamImpl<T> extends BaseParamImpl<T> implements ValParam<T> {

	ValParamImpl(Class<T> paramType) {
		super(paramType);
	}

	@Override
	boolean check(Object value) {
		return getParamType().equals(value.getClass());
	}

	@Override
	boolean isList() {
		return true;
	}
}
