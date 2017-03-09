package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;

final class CompiledSelectSources_ListAllOfOneEntity extends CompiledSelectSources<CompiledSelectSource> {
	
	private final SharedSelectSource selectSource;
	
	private static CompiledSelectSource makeSelectSource(SharedSelectSource selectSource) {
		
		final Class<?> type = selectSource.getType();
		
		return new CompiledSelectSource_Named(type, CompiledSelectSource_Named.getNameNoCheck(type), 0);
	}
	
	CompiledSelectSources_ListAllOfOneEntity(SharedSelectSource selectSource) {
		super(Arrays.asList(makeSelectSource(selectSource)));
		
		if (selectSource == null) {
			throw new IllegalArgumentException("selectSource == null");
		}

		this.selectSource = selectSource;
	}

	@Override
	public CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		throw new UnsupportedOperationException("Expected empty");
	}

	@Override
	TypeMapSource getNamedSource(Class<?> type) {
		throw new UnsupportedOperationException("Expected empty");
	}

	@Override
	TypeMapSource getAliasesSource(IAlias alias) {
		throw new UnsupportedOperationException("Expected empty");
	}

	@Override
	int getSourceIdx(SharedSelectSource source) {

		if (!source.equals(selectSource)) {
			throw new IllegalStateException("Not matching expected source");
		}
		
		// Only one source
		return 0;
	}
}
