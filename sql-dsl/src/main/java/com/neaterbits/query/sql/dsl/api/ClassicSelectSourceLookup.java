package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class ClassicSelectSourceLookup extends SelectSourceLookup {

	
	private final CompiledSelectSources<?> compiledSources;
	private final CompiledGetterSetterCache cache;
	
	
	public ClassicSelectSourceLookup(CompiledSelectSources<?> compiledSources) {
		
		if (compiledSources == null) {
			throw new IllegalArgumentException("compiledSources == null");
		}
		
		this.compiledSources = compiledSources;
		this.cache = new CompiledGetterSetterCache();
	}

	@Override
	CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter) throws CompileException {
		return compiledSources.makeFieldReference(original, inputGetter, cache);
	}
	
	@Override
	TypeMapSource getNamedSource(Class<?> type) {
		return compiledSources.getNamedSource(type);
	}
	
	@Override
	TypeMapSource getAliasesSource(IAlias alias) {
		return compiledSources.getAliasesSource(alias);
	}
	
	@Override
	CompiledGetterFunction findGetter(Function<?, ?> getter) throws CompileException {
		return (CompiledGetterFunction)cache.findGetterFromTypes(compiledSources.getTypes(), getter);
	}
	
	@Override
	CompiledSelectSources<?> compile(CollectedQueryResult queryResult) {
		return compiledSources;
	}
}
