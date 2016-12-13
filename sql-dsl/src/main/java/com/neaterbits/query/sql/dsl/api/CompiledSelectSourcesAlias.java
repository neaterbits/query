package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledSelectSourcesAlias extends CompiledSelectSources<CompiledSelectSourceAlias> {

	CompiledSelectSourcesAlias(SelectSourceImpl original, List<CompiledSelectSourceAlias> sources) {
		super(original, sources);
	}


	@Override
	public CompiledFieldReference makeFieldReference(QueryBuilderItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		
		return TypeMapBase.makeFieldReferenceFromAliases(
				original,
				inputGetter,
				cache,
				getSources(),
				(CompiledSelectSourceAlias source) -> source.getAlias());
	}


	@Override
	TypeMapSource getClassesSource(Class<?> type) {
		throw new IllegalStateException("Expected aliases: " + type);
	}


	@Override
	TypeMapSource getAliasesSource(IAlias alias) {
		
		for (CompiledSelectSourceAlias compiled : getSources()) {
			if (compiled.getAlias() == alias) {
				return compiled;
			}
		}
		
		throw new IllegalStateException("Unable to find alias " + alias);
	}
}
