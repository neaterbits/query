package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class InParamImpl<T> extends BaseParamImpl<T> implements InParam<T> {

	InParamImpl(Class<T> paramType) {
		super(paramType);
	}

	@Override
	boolean check(Object value) {
		
		if (!(value instanceof List<?>)) {
			throw new IllegalArgumentException("Expected list param");
		}
		
		final List<?> list = (List<?>)value;
		
		if (list.isEmpty()) {
			throw new IllegalArgumentException("no entries in list");
		}
		
		for (Object o : list) {
			if (!getParamType().isAssignableFrom(o.getClass())) {
				throw new IllegalArgumentException("item class " + o.getClass() + " not assignable from " + getParamType());
			}
		}
		
		return true;
	}

	@Override
	boolean isList() {
		return true;
	}
}
