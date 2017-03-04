package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;

final class ClassicSelectSourceLookup implements SelectSourceLookup {

	
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
	public CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter) throws CompileException {
		return compiledSources.makeFieldReference(original, inputGetter, cache);
	}
	
	@Override
	public TypeMapSource getNamedSource(Class<?> type) {
		return compiledSources.getNamedSource(type);
	}
	
	@Override
	public TypeMapSource getAliasesSource(IAlias alias) {
		return compiledSources.getAliasesSource(alias);
	}
	
	@Override
	public CompiledGetterFunction findGetter(Function<?, ?> getter) throws CompileException {
		return (CompiledGetterFunction)cache.findGetterFromTypes(compiledSources.getTypes(), getter);
	}
	
	@Override
	public CompiledSetter compileSetterUntyped(Class<?> resultType, BiConsumer<?, ?> setter) {
		return cache.compileSetterUntyped(resultType, setter);
	}
	
	@Override
	public CompiledSelectSources<?> compile() {
		return compiledSources;
	}
}
