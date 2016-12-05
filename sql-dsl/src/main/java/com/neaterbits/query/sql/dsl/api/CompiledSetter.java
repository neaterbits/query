package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.function.BiConsumer;

final class CompiledSetter {
	private final BiConsumer<?, ?> setter;
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

	BiConsumer<?, ?> getSetter() {
		return setter;
	}

	Method getSetterMethod() {
		return setterMethod;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	void set(Object o, Object val) {
		((BiConsumer)setter).accept(o, val);
	}
	
}
