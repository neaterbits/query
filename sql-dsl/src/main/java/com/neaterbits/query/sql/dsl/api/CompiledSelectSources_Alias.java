package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledSelectSources_Alias extends CompiledSelectSources<CompiledSelectSource_Alias> {

	CompiledSelectSources_Alias(CollectedSelectSource original, List<CompiledSelectSource_Alias> sources) {
		super(original, sources);
	}


	@Override
	public CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		
		return TypeMapBase.makeFieldReferenceFromAliases(
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
		
		final SharedSelectSource_Alias aliasSource = (SharedSelectSource_Alias)source;
		
		for (int i = 0; i < getSources().size(); ++ i) {
			if (aliasSource.getAlias() == getSources().get(i).getAlias()) {
				return i;
			}
		}
		
		return -1;
	}
}
