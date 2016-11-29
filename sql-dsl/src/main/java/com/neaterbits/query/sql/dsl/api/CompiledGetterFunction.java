package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.function.Function;

final class CompiledGetterFunction extends CompiledGetter {
	private final Function<?, ?> getter;

	CompiledGetterFunction(Function<?, ?> getter, Method getterMethod) {
		super(getterMethod);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		
		this.getter = getter;
	}

	Function<?, ?> getGetter() {
		return getter;
	}
}
