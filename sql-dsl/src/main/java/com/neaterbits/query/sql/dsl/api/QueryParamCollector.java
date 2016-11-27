package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

final class QueryParamCollector {

	private final List<QueryParamAndValue> collected;

	QueryParamCollector() {
		this.collected = new ArrayList<>();
	}

	void add(Param<?> param, Object value) {
		
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		final ParamImpl<?> impl = (ParamImpl<?>)param;
		
		if (value != null && !impl.getParamType().equals(value.getClass())) {
			throw new IllegalArgumentException("Mismatch between param class " + param.getClass().getSimpleName()
						+ " and value class " + value.getClass().getSimpleName());
		}

		collected.add(new QueryParamAndValue(impl, value));
	}

	List<QueryParamAndValue> getCollected() {
		return collected;
	}
}

