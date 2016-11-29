package com.neaterbits.query.sql.dsl.api;


final class SelectSourceAliasesImpl extends SelectSourceImpl {

	
	public SelectSourceAliasesImpl(Object [] aliases) {
		super(getTypes(aliases));
	}
	
	@Override
	CompiledGetter compileGetter(CollectedMapping mapping, CompiledGetterSetterCache cache) throws CompileException {
		
		final CollectedMappingAlias aliasMapping = (CollectedMappingAlias)mapping;

		final CompiledGetter ret = cache.findGetterFromTypes(getTypes(), aliasMapping.getGetter());
		
		if (ret == null) {
			throw new IllegalStateException("Failed to find getter method for alias");
		}
		

		return ret;
	}
	
	private static Class<?> [] getTypes(Object [] aliases) {

		if (aliases == null) {
			throw new IllegalArgumentException("aliases == null");
		}
		
		if (aliases.length == 0) {
			throw new IllegalArgumentException("no aliases");
		}
		
		final Class<?> [] ret = new Class[aliases.length];
		
		for (int i = 0; i < aliases.length; ++ i) {
			final Object alias = aliases[i];
			
			if (alias == null) {
				throw new IllegalArgumentException("null alias");
			}
			
			if (alias instanceof Class<?>) {
				throw new IllegalArgumentException("alias is a class");
			}
			
			if (!(alias instanceof IAlias)) {
				throw new IllegalArgumentException("Alias not obtained with alias() helper");
			}

			final IAlias iAlias = (IAlias)alias;
			
			aliases[i] = iAlias.getType();
		}
		
		return ret;
	}
}
