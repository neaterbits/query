package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
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
		
		return m == null ? null : new CompiledGetter(getter, m);
	}

	CompiledGetter compileGetterUntyped(Class<?> cl, Function<?, ?> getter) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Method m = MethodFinder.findOrNull((Class)cl, (Function)getter);
		
		return m == null ? null : new CompiledGetter(getter, m);
	}
	
	<T, R> CompiledSetter compileSetterTyped(Class<T> cl, BiConsumer<T, R> setter) {
		final Method m = MethodFinder.findOrNull(cl, setter);
		
		return m == null ? null : new CompiledSetter(setter, m);
	}

	<T, R> CompiledSetter compileSetterUntyped(Class<?> cl, BiConsumer<?, ?> setter) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Method m = MethodFinder.findOrNull((Class)cl, (BiConsumer)setter);
		
		return m == null ? null : new CompiledSetter(setter, m);
	}
}
