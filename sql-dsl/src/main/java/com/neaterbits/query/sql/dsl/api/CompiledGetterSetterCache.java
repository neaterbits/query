package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.neaterbits.query.util.java8.MethodFinder;

/**
 * Cache for finding getters/setters
 * TODO: can cache cglib enhances from MethodFinder but not bother for now.
 * 
 * @author nhl
 *
 */

final class CompiledGetterSetterCache {

	<T, R> CompiledGetter compileGetterTyped(Class<T> cl, Function<T, R> getter) {
		final Method m = MethodFinder.findOrNull(cl, getter);
		
		return m == null ? null : new CompiledGetterFunction(getter, m);
	}

	CompiledGetter compileGetterUntyped(Class<?> cl, Function<?, ?> getter) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Method m = MethodFinder.findOrNull((Class)cl, (Function)getter);
		
		return m == null ? null : new CompiledGetterFunction(getter, m);
	}
	
	<T, R> CompiledSetter compileSetterTyped(Class<T> cl, BiConsumer<T, R> setter) {
		final Method m = MethodFinder.findOrNull(cl, setter);
		
		return m == null ? null : new CompiledSetter(setter, m);
	}

	<T, R> CompiledSetter compileSetterUntyped(Class<?> cl, BiConsumer<?, ?> setter) {
		
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Method m = MethodFinder.findOrNull((Class)cl, (BiConsumer)setter);
		
		return m == null ? null : new CompiledSetter(setter, m);
	}
	
	
	CompiledGetter findGetterFromTypesArray(Class<?> [] types, Function<?, ?> getter) throws CompileException {
		return findGetterFromTypes(Arrays.asList(types), getter);
	}
	
	CompiledGetter findGetterFromTypes(Iterable<Class<?>> types, Function<?, ?> getter) throws CompileException {
		return findGetterFromTypes(types, getter, type -> type);
	}

	<T> CompiledGetter findGetterFromTypes(Iterable<T> iter, Function<?, ?> getter, Function<T, Class<?>> mapper) throws CompileException {
		
		CompiledGetter ret = null;
		
		for (T t : iter) {
			
			final Class<?> type = mapper.apply(t);
			
			CompiledGetter found;
			
			try {
				found = compileGetterUntyped(type, getter);
			}
			catch (ClassCastException ex) {
				// Instance not of right type
				continue;
			}
			
			if (found != null) {
				if (ret != null) {
					
					// !! NOTE !! may find the same method multiple times, when using inheritance, eg. Square and Cirle are Shapes with inherited Shape::getArea
					
					if (!ret.equals(found)) {
						throw new CompileException("More than one getter found: " + found + " and " + ret);
					}
				}
				
				ret = found;
			}
		}

		return ret;
	}
}
