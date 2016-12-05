package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

final class QueryParamCollector implements ParamValueResolver {

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

	private List<QueryParamAndValue> getCollected() {
		return collected;
	}

	@Override
	public Object resolveParam(Param<?> param) {
		
		// Find value
		for (QueryParamAndValue paramAndValue : getCollected()) {
			if (paramAndValue.getParam() == param) {
				return paramAndValue.getValue();
			}
		}
		
		return null;
	}
}

