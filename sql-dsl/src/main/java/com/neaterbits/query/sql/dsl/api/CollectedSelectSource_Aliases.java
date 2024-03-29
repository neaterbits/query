package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.function.Supplier;


final class CollectedSelectSource_Aliases extends CollectedSelectSource {

	private final IAlias [] aliases;

	public CollectedSelectSource_Aliases(Object [] aliases) {
		super(getTypes(aliases));

		this.aliases = new IAlias[aliases.length];

		for (int i = 0; i < aliases.length; ++ i) {
			this.aliases[i] = (IAlias)aliases[i];
		}
	}

	IAlias[] getAliases() {
		return aliases;
	}
	
	@Override
	CompiledGetter compileGetter(CollectedMapping mapping, CompiledGetterSetterCache cache) throws CompileException {
		
		final CollectedMapping_Alias aliasMapping = (CollectedMapping_Alias)mapping;

		final Supplier<?> getter = aliasMapping.getSupplierGetter();
		
		// Trigger supplier
		getter.get();

		Method lastMethod = null;
		// Clear all aliases
		for (IAlias alias : aliases) {
			 lastMethod = alias.getLastInvokedMethod();
			 
			 if (lastMethod != null) {
				 break;
			 }
		}

		if (lastMethod == null) {
			throw new IllegalArgumentException("Method not invoked on alias, not an alias Supplier");
		}

		return new CompiledGetterSupplier(getter, lastMethod);
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
			
			ret[i] = iAlias.getType();
		}
		
		return ret;
	}
}
