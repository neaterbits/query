package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class TypeMapBase {

	static <T extends TypeMapSource> CompiledFieldReference makeFieldReferenceFromClasses(
			QueryBuilderItem original,
			Getter inputGetter,
			CompiledGetterSetterCache cache,
			Class<?> [] types,
			Iterable<T> iterable) throws CompileException {

		final FunctionGetter functionGetter = (FunctionGetter)inputGetter;
		
		final CompiledGetter compiledGetter = cache.findGetterFromTypesArray(
				types,
				functionGetter.getter);
		
		if (compiledGetter == null) {
			throw new CompileException("No getter found: " + functionGetter + " among types " + Arrays.toString(types));
		}

		final Class<?> type = compiledGetter.getGetterMethod().getDeclaringClass();

		T found = null;

		// Find compiled
		for (T compiled : iterable) {
			if (compiled.getType().equals(type)) {
				found = compiled;
				break;
			}
		}

		if (found == null) {
			throw new IllegalStateException("Unable to find compiled source for type " + type.getName());
		}

		return new CompiledFieldReference(original, found, compiledGetter);
	}
	
	static<T extends TypeMapSource> CompiledFieldReference makeFieldReferenceFromAliases(
			QueryBuilderItem original,
			Getter inputGetter,
			CompiledGetterSetterCache cache,
			Iterable<T> sources,
			Function<T, IAlias> aliasGetter) throws CompileException {

		final SupplierGetter supplierGetter = (SupplierGetter)inputGetter;

		final Supplier<?> getter = supplierGetter.getGetter();
		
		// Trigger supplier
		getter.get();
		
		T foundSelectSource = null;
		
		// Find the alias and method
		Method lastMethod = null;
		for (T source : sources) {

			 final IAlias alias = aliasGetter.apply(source);
			 lastMethod = alias.getLastInvokedMethod();
			 
			 if (lastMethod != null) {
				 foundSelectSource = source;
				 break;
			 }
		}

		if (lastMethod == null) {
			throw new IllegalArgumentException("Method not invoked on alias, not an alias Supplier");
		}

		final CompiledGetter compiledGetter = new CompiledGetterSupplier(getter, lastMethod);
		
		return new CompiledFieldReference(original, foundSelectSource, compiledGetter);
	}
}
