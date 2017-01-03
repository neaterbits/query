package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;

final class AdhocConditionsList<MODEL> extends AdhocConditions<MODEL, List<Object>, AdhocQueryNamed_List<MODEL>> 

	implements IAdhocAndOrLogicalClausesList<MODEL, Object, List<Object>> {

	AdhocConditionsList(AdhocQueryNamed_List<MODEL> query, int level) {
		super(query, level);
	}

	@Override
	public <L extends List<Object>> L as(Function<List<Object>, L> function) {
		return query.as(function);
	}
}
