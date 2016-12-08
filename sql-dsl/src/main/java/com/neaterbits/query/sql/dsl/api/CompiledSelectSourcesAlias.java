package com.neaterbits.query.sql.dsl.api;

import java.util.List;

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
