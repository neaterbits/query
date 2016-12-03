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

	Supplier<?> getGetter() {
		return getter;
	}
}
