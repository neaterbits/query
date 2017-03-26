package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.neaterbits.query.util.java8.MethodUtil;

final class CompiledSelectSources_Alias extends CompiledSelectSources<CompiledSelectSource_Alias> {

	CompiledSelectSources_Alias(CollectedSelectSource original, List<CompiledSelectSource_Alias> sources) {
		super(original, sources);
	}

	CompiledSelectSources_Alias(List<CompiledSelectSource_Alias> sources) {
		super(sources);
	}

	@Override
	public CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		
		return makeFieldReferenceFromAliases(
				original,
				inputGetter,
				cache,
				getSources(),
				(CompiledSelectSource_Alias source) -> source.getAlias());
	}


	@Override
	TypeMapSource getNamedSource(Class<?> type) {
		throw new IllegalStateException("Expected aliases: " + type);
	}


	@Override
	TypeMapSource getAliasesSource(IAlias alias) {
		
		for (CompiledSelectSource_Alias compiled : getSources()) {
			if (compiled.getAlias() == alias) {
				return compiled;
			}
		}
		
		throw new IllegalStateException("Unable to find alias " + alias);
	}

	@Override
	int getSourceIdx(SharedSelectSource source) {
		
		if (source instanceof SharedSelectSource_Named) {
			final SharedSelectSource_Named classSource = (SharedSelectSource_Named)source;

			int found = -1;
			
			for (int i = 0; i < getSources().size(); ++ i) {
				if (classSource.getType().equals(getSources().get(i).getType())) {
					if (found != -1) {
						throw new IllegalStateException("Multiple matching aliasesfound for result, list(alias) or one(alias) instead of list(<class>) or one(<class>) ");
					}
					
					found = i;
				}
			}
			
			return found;

		}
		else {
		
			final SharedSelectSource_Alias aliasSource = (SharedSelectSource_Alias)source;
			
			for (int i = 0; i < getSources().size(); ++ i) {
				if (aliasSource.getAlias() == getSources().get(i).getAlias()) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	static class AliasMethod<T> {
		final T element;
		final Method method;
		
		public AliasMethod(T element, Method method) {
			this.element = element;
			this.method = method;
		}
	}

	static <T> AliasMethod<T> findMethodForAlias(SupplierGetter supplierGetter, Iterable<T> sources, Function<T, IAlias> aliasGetter) {

		final Supplier<?> getter = supplierGetter.getGetter();
		
		// Trigger supplier
		getter.get();
		
		Method lastMethod = null;
		T foundSelectSource = null;
		
		for (T source : sources) {

			 final IAlias alias = aliasGetter.apply(source);
			 lastMethod = alias.getLastInvokedMethod();

			 if (lastMethod != null && MethodUtil.getClassWithOriginalPrototypeForMethod(lastMethod).equals(alias.getType())) {
				 foundSelectSource = source;
				 break;
			 }
		}

		if (lastMethod == null) {
			throw new IllegalArgumentException("Method not invoked on alias, not an alias Supplier");
		}

		return new AliasMethod<>(foundSelectSource, lastMethod);
	}
	
	private static<T extends TypeMapSource> CompiledFieldReference makeFieldReferenceFromAliases(
			CollectedItem original,
			Getter inputGetter,
			CompiledGetterSetterCache cache,
			Iterable<T> sources,
			Function<T, IAlias> aliasGetter) throws CompileException {

		final SupplierGetter supplierGetter = (SupplierGetter)inputGetter;

		
		final AliasMethod<T> aliasMethod = findMethodForAlias(supplierGetter, sources, aliasGetter);
		
		
		// Find the alias and method

		final CompiledGetter compiledGetter = new CompiledGetterSupplier(supplierGetter.getGetter(), aliasMethod.method);
		
		return new CompiledFieldReference(original, aliasMethod.element, compiledGetter);
	}
}
