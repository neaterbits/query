package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Collect fields, for order by and group by
abstract class Collector_Fields {

	private final List<FunctionGetter> getters;

	Collector_Fields() {
		this.getters = new ArrayList<>();
	}

	final <T, R> void add(Function<T, R> function) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		getters.add(new FunctionGetter(function));
	}
}
