package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.List;

final class CompiledSelectSourcesClass extends CompiledSelectSources<CompiledSelectSourceClass> {

	CompiledSelectSourcesClass(SelectSourceImpl original, List<CompiledSelectSourceClass> sources) {
		super(original, sources);
	}

	@Override
	CompiledFieldReference makeFieldReference(QueryBuilderItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {

		final FunctionGetter functionGetter = (FunctionGetter)inputGetter;
		
		final CompiledGetter compiledGetter = cache.findGetterFromTypes(
				getOriginal().getTypes(),
				functionGetter.getter);
		
		if (compiledGetter == null) {
			throw new CompileException("No getter found: " + functionGetter + " among types " + Arrays.toString(getOriginal().getTypes()));
		}

		final Class<?> type = compiledGetter.getGetterMethod().getDeclaringClass();

		CompiledSelectSourceClass found = null;

		// Find compiled
		for (CompiledSelectSourceClass compiled : getSources()) {
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

