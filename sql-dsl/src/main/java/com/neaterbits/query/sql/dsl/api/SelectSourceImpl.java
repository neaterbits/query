package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

abstract class SelectSourceImpl implements SelectSources {

	private final Class<?> [] types;
	
	SelectSourceImpl(Class<?> [] types) {
		
		if (types == null) {
			throw new IllegalArgumentException("types == null");
		}
		
		this.types = types;
	}

	@Override
	public Class<?>[] getTypes() {
		return types;
	}

	final CompiledGetter compileGetter(Function<?, ?> getter, CompiledGetterSetterCache cache) throws CompileException {

		final CompiledGetter ret = cache.findGetterFromTypes(types, getter);
		
		if (ret == null) {
			throw new CompileException("No getter found: " + getter);
		}
		
		return ret;
	}
}
