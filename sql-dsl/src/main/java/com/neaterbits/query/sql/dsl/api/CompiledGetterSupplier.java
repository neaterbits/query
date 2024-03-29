package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.function.Supplier;

final class CompiledGetterSupplier extends CompiledGetter {

	private final Supplier<?> getter;

	CompiledGetterSupplier(Supplier<?> getter, Method getterMethod) {
		super(getterMethod);

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}

	@Override
	Object execute(Object instance) {
		// TODO
		System.err.println(getClass().getSimpleName() + ": Base class calls reflection, TODO: more optimal way");
		
		return super.execute(instance);
	}

	Supplier<?> getGetter() {
		return getter;
	}
}
