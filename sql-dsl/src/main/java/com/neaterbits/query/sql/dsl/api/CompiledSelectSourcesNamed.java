package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledSelectSourcesNamed extends CompiledSelectSources<CompiledSelectSourceNamed> {

	CompiledSelectSourcesNamed(SelectSourceImpl original, List<CompiledSelectSourceNamed> sources) {
		super(original, sources);
	}

	@Override
	public CompiledFieldReference makeFieldReference(QueryBuilderItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		return TypeMapBase.makeFieldReferenceFromClasses(original, inputGetter, cache, getSources(), s -> s.getType());
	}

	@Override
	TypeMapSource getNamedSource(Class<?> type) {
		
		for (CompiledSelectSourceNamed compiled : getSources()) {
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
	int getSourceIdx(SelectSource source) {
		final SelectSourceNamed classSource = (SelectSourceNamed)source;
		
		
		for (int i = 0; i < getSources().size(); ++ i) {
			if (classSource.getType().equals(getSources().get(i).getType())) {
				return i;
			}
		}
		
		return -1;
	}
}

