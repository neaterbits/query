package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;

final class CompiledSelectSourcesAlias extends CompiledSelectSources<CompiledSelectSourceAlias> {

	CompiledSelectSourcesAlias(SelectSourceImpl original, List<CompiledSelectSourceAlias> sources) {
		super(original, sources);
	}


	@Override
	CompiledFieldReference makeFieldReference(QueryBuilderItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		final SupplierGetter supplierGetter = (SupplierGetter)inputGetter;

		final Supplier<?> getter = supplierGetter.getGetter();
		
		// Trigger supplier
		getter.get();
		
		CompiledSelectSourceAlias foundSelectSource = null;
		
		// Find the alias and method
		Method lastMethod = null;
		for (CompiledSelectSourceAlias source : getSources()) {

			 final IAlias alias = source.getAlias();
			 lastMethod = alias.getLastInvokedMethod();
			 
			 if (lastMethod != null) {
				 foundSelectSource = source;
				 break;
			 }
		}

		if (lastMethod == null) {
			throw new IllegalArgumentException("Method not invoked on alias, not an alias Supplier");
		}

		final CompiledGetter compiledGetter = new CompiledGetterSupplier(getter, lastMethod);
		
		return new CompiledFieldReference(original, foundSelectSource, compiledGetter);
	}
}
