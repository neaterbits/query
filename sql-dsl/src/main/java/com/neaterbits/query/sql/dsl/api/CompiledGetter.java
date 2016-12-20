package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class CompiledGetter {

	private final Method getterMethod;
	
	CompiledGetter(Method getterMethod) {
		
		if (getterMethod == null) {
			throw new IllegalArgumentException("getterMethod == null");
		}
		
		this.getterMethod = getterMethod;
	}

	// TODO: Call lambda
	Object execute(Object instance) {

		final Object ret;

		try {
			ret = getterMethod.invoke(instance);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			throw new IllegalStateException("Failed to invoke getter on" + instance.getClass().getName() + ": " + getterMethod, ex);
		}

		return ret;
	}
	
	final Method getGetterMethod() {
		return getterMethod;
	}
}

