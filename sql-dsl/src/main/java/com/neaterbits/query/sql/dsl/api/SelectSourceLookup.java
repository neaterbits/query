package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;


abstract class SelectSourceLookup {

	final CompiledGetterSetterCache cache;
	
	SelectSourceLookup() {
		this.cache = new CompiledGetterSetterCache();
	}

	abstract CompiledSelectSources<?> compile();
	
	abstract TypeMapSource getNamedSource(Class<?> type);
	abstract TypeMapSource getAliasesSource(IAlias alias);
	
	abstract CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter) throws CompileException;
	
	
	// For mapping return-types
	//abstract CompiledSetter compileSetterUntyped(Class<?> resultType, BiConsumer<?, ?> setter);
	
	// TODO : for process-result, must support alias as well
	abstract CompiledGetterFunction findGetter(Function<?, ?> getter) throws CompileException;

	final CompiledSetter compileSetterUntyped(Class<?> resultType, BiConsumer<?, ?> setter) {
		return cache.compileSetterUntyped(resultType, setter);
	}
}