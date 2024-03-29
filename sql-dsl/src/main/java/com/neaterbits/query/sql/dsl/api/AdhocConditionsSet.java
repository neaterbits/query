package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

final class AdhocConditionsSet<MODEL, RESULT>
			extends AdhocConditions<MODEL, Set<Object>, AdhocQuery_Named_Set<MODEL>> 

	implements IAdhocLogical_And_Or_Set<MODEL, Object, Set<Object>>{

	AdhocConditionsSet(AdhocQuery_Named_Set<MODEL> query, int level) {
		super(query, level);
	}

	@Override
	public <L extends Set<Object>> L as(Function<Collection<Object>, L> function) {
		return query.as(function);
	}
}
