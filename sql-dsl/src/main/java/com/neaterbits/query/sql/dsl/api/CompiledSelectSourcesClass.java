package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledSelectSourcesClass extends CompiledSelectSources<CompiledSelectSourceClass> {

	CompiledSelectSourcesClass(SelectSourceImpl original, List<CompiledSelectSourceClass> sources) {
		super(original, sources);
	}

	@Override
	CompiledFieldReference makeFieldReference(CollectedMapping mapping, CompiledGetterSetterCache cache) throws CompileException {

		final CollectedMappingTable tableMapping = (CollectedMappingTable)mapping;
		
		final CompiledGetter compiledGetter = cache.findGetterFromTypes(getOriginal().getTypes(), tableMapping.getGetter());
		
		if (compiledGetter == null) {
			throw new CompileException("No getter found: " + tableMapping.getGetter());
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

		return new CompiledFieldReference(mapping.getOriginal(), found, compiledGetter);
	}
}

