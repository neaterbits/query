package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledSelectSourcesClass extends CompiledSelectSources<CompiledSelectSourceClass> {

	CompiledSelectSourcesClass(SelectSourceImpl original, List<CompiledSelectSourceClass> sources) {
		super(original, sources);
	}

	@Override
	public CompiledFieldReference makeFieldReference(QueryBuilderItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException {
		return TypeMapBase.makeFieldReferenceFromClasses(original, inputGetter, cache, getSources(), s -> s.getType());
	}
}

