package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;

abstract class CompiledGetter {

	private final Method getterMethod;

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

