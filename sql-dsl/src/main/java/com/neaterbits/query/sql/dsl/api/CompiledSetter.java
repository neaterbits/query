package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.function.BiConsumer;

final class CompiledSetter {
	@SuppressWarnings("rawtypes")
	private final BiConsumer setter;
	private final Method setterMethod;

	CompiledSetter(BiConsumer<?, ?> getter, Method setterMethod) {
		
		if (getter == null) {
			throw new IllegalArgumentException("setter == null");
		}
		
		if (setterMethod == null) {
			throw new IllegalArgumentException("setterMethod == null");
		}
		
		this.setter = getter;
		this.setterMethod = setterMethod;
	}

	@SuppressWarnings("unchecked")
	void execute(Object input, Object value) {
		setter.accept(input, value);
	}
	
	BiConsumer<?, ?> getSetter() {
		return setter;
	}

	Method getSetterMethod() {
		return setterMethod;
	}
}
