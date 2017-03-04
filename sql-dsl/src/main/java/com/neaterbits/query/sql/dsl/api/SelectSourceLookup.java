package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;

interface SelectSourceLookup {

	CompiledSelectSources<?> compile();
	
	TypeMapSource getNamedSource(Class<?> type);
	TypeMapSource getAliasesSource(IAlias alias);
	
	CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter) throws CompileException;
	
	
	// For mapping return-types
	CompiledSetter compileSetterUntyped(Class<?> resultType, BiConsumer<?, ?> setter);
	
	// TODO : for process-result, must support alias as well
	CompiledGetterFunction findGetter(Function<?, ?> getter) throws CompileException;

}
