package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;

final class AdhocConditions_List<MODEL> extends AdhocConditions<MODEL, List<Object>, AdhocQuery_Named_List<MODEL>> 

	implements IAdhocLogical_And_Or_List<MODEL, Object, List<Object>> {

	AdhocConditions_List(AdhocQuery_Named_List<MODEL> query, int level) {
		super(query, level);
	}

	@Override
	public <L extends List<Object>> L as(Function<List<Object>, L> function) {
		return query.as(function);
	}
}
