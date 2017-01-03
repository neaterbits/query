package com.neaterbits.query.sql.dsl.api;


abstract class CollectedSelectSource
			extends CollectedItem
			implements SharedISelectSources {

	private final Class<?> [] types;

	abstract CompiledGetter compileGetter(CollectedMapping mapping, CompiledGetterSetterCache cache) throws CompileException;

	CollectedSelectSource(Class<?> [] types) {
		
		if (types == null) {
			throw new IllegalArgumentException("types == null");
		}
		
		this.types = types;
	}

	@Override
	public final Class<?>[] getTypes() {
		return types;
	}
}
