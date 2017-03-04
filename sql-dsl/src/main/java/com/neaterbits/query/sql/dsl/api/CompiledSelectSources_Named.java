package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;

import com.neaterbits.util.compat.Coll;

final class CompiledSelectSources_Named extends CompiledSelectSources<CompiledSelectSource_Named> {

	CompiledSelectSources_Named(CollectedSelectSource original, List<CompiledSelectSource_Named> sources) {
		super(original, sources);
	}

	CompiledSelectSources_Named(List<CompiledSelectSource_Named> sources) {
		super(sources);
	}
	
	@Override
	public CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		return makeFieldReferenceFromClasses(original, inputGetter, cache, getSources(), s -> s.getType());
	}

	@Override
	TypeMapSource getNamedSource(Class<?> type) {
		
		for (CompiledSelectSource_Named compiled : getSources()) {
			if (compiled.getType().equals(type)) {
				return compiled;
			}
		}

		throw new IllegalStateException("Unknown class source " + type);
	}

	@Override
	TypeMapSource getAliasesSource(IAlias alias) {
		throw new IllegalStateException("Expected type: " + alias);
	}
	
	@Override
	int getSourceIdx(SharedSelectSource source) {
		final SharedSelectSource_Named classSource = (SharedSelectSource_Named)source;
		
		
		for (int i = 0; i < getSources().size(); ++ i) {
			if (classSource.getType().equals(getSources().get(i).getType())) {
				return i;
			}
		}
		
		return -1;
	}
	
	private static <T extends TypeMapSource> CompiledFieldReference makeFieldReferenceFromClasses(
			CollectedItem original,
			Getter inputGetter,
			CompiledGetterSetterCache cache,
			Iterable<T> iterable,
			Function<T, Class<?>> classGetter) throws CompileException {

		final FunctionGetter functionGetter = (FunctionGetter)inputGetter;
		
		final CompiledGetter compiledGetter = cache.findGetterFromTypes(
				iterable, 
				functionGetter.getter,
				classGetter);
		
		if (compiledGetter == null) {
			throw new CompileException("No getter found: " + functionGetter + " among types " + Coll.toArrayList(iterable));
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
	
}

