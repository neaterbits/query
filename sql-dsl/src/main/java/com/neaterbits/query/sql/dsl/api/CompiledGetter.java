package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.function.Function;

final class CompiledGetter {

	private final Function<?, ?> getter;
	private final Method getterMethod;

	CompiledGetter(Function<?, ?> getter, Method getterMethod) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		if (getterMethod == null) {
			throw new IllegalArgumentException("getterMethod == null");
		}
		
		this.getter = getter;
		this.getterMethod = getterMethod;
	}

	Function<?, ?> getGetter() {
		return getter;
	}

	Method getGetterMethod() {
		return getterMethod;
	}
}

