package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.function.Function;

abstract class SelectSourceImpl {

	private final Class<?> [] types;
	
	SelectSourceImpl(Class<?> [] types) {
		
		if (types == null) {
			throw new IllegalArgumentException("types == null");
		}
		
		this.types = types;
	}

	final CompiledGetter compileGetter(Function<?, ?> getter, CompiledGetterSetterCache cache) throws CompileException {
		CompiledGetter ret = null;
		
		
		for (Class<?> type : types) {
			final CompiledGetter found = cache.compileGetterUntyped(type, getter);
			
			if (found != null) {
				if (ret != null) {
					throw new CompileException("More than one getter found: " + getter);
				}
				
				ret = found;
			}
		}

		if (ret == null) {
			throw new CompileException("No getter found: " + getter);
		}
		
		return ret;
	}
}
