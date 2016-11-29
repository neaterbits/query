package com.neaterbits.query.sql.dsl.api;


final class SelectSourceAliasAliasesImpl extends SelectSourceImpl {
	
	private final Alias<?> [] aliases;

	SelectSourceAliasAliasesImpl(Alias<?> [] aliases) {
		super(getTypeArray(aliases));
		
		if (aliases == null) {
			throw new IllegalArgumentException("aliases == null");
		}
		
		this.aliases = aliases;
	}

	Alias<?> [] getAliases() {
		return aliases;
	}
	
	private static Class<?> [] getTypeArray(Alias<?> ... aliases) {
		final Class<?> [] ret = new Class[aliases.length]; 
		
		for (int i = 0; i < aliases.length; ++ i) {
			ret[i] = ((AliasImpl<?>)aliases[i]).getAliasType();
		}
		
		return ret;
	}

	@Override
	CompiledGetter compileGetter(CollectedMapping mapping,
			CompiledGetterSetterCache cache) throws CompileException {

		throw new UnsupportedOperationException("TODO");
	}
}
