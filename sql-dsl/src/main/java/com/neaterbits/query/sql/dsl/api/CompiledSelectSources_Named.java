package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledSelectSources_Named extends CompiledSelectSources<CompiledSelectSource_Named> {

	CompiledSelectSources_Named(CollectedSelectSource original, List<CompiledSelectSource_Named> sources) {
		super(original, sources);
	}

	@Override
	public CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		return TypeMapBase.makeFieldReferenceFromClasses(original, inputGetter, cache, getSources(), s -> s.getType());
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
}

