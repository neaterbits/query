package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;

final class AdhocConditionsList<MODEL> extends AdhocConditions<MODEL, List<Object>, AdhocQueryClassList<MODEL>> 

	implements IAdhocAndOrLogicalClausesList<MODEL, Object, List<Object>>

{
	AdhocConditionsList(AdhocQueryClassList<MODEL> query, int level, Function<?, ?> function) {
		super(query, level, function);
	}

	@Override
	public <L extends List<Object>> L as(Function<List<Object>, L> function) {
		return query.as(function);
	}
}
