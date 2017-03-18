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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getterMethod == null) ? 0 : getterMethod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompiledGetter other = (CompiledGetter) obj;
		if (getterMethod == null) {
			if (other.getterMethod != null)
				return false;
		} else if (!getterMethod.equals(other.getterMethod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [getterMethod=" + getterMethod + "]";
	}
}

