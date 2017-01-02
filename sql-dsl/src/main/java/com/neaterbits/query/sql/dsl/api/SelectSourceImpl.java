package com.neaterbits.query.sql.dsl.api;


abstract class SelectSourceImpl
			extends QueryBuilderItem
			implements SelectSources {

	private final Class<?> [] types;

	abstract CompiledGetter compileGetter(CollectedMapping mapping, CompiledGetterSetterCache cache) throws CompileException;

	SelectSourceImpl(Class<?> [] types) {
		
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
