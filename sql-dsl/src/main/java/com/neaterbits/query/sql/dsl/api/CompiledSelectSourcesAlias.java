package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;

final class CompiledSelectSourcesAlias extends CompiledSelectSources<CompiledSelectSourceAlias> {

	CompiledSelectSourcesAlias(SelectSourceImpl original, List<CompiledSelectSourceAlias> sources) {
		super(original, sources);
	}


	@Override
	public CompiledFieldReference makeFieldReference(QueryBuilderItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		
		return TypeMapBase.makeFieldReferenceFromAliases(
				original,
				inputGetter,
				cache,
				getSources(),
				(CompiledSelectSourceAlias source) -> source.getAlias());
	}
}
