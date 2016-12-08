package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class CompiledGetter {

	private final Method getterMethod;

	// TODO: Call lambda
	Object execute(Object instance) {
		try {
			return getterMethod.invoke(instance);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			throw new IllegalStateException("Failed to invoke getter", ex);
		}
	}
	
	CompiledGetter(Method getterMethod) {
		
		if (getterMethod == null) {
			throw new IllegalArgumentException("getterMethod == null");
		}
		
		this.getterMethod = getterMethod;
	}

	final Method getGetterMethod() {
		return getterMethod;
	}
}

