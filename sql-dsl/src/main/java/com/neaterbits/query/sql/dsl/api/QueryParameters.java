package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * List of parameters from query conditions
 * 
 * @author nhl
 *
 */

class QueryParameters {

	private final List<Param<?>> params;

	QueryParameters(List<Param<?>> params) {
		
		if (params == null) {
			throw new IllegalArgumentException("params == null");
		}
		
		this.params = params;
	}
	
	int getIndexOf(Param<?> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}

		return params.indexOf(param);
	}

	void forEach(BiConsumer<Param<?>, Integer> consumer) {
		final int num = params.size();
		
		for (int i = 0; i < num; ++ i) {
			consumer.accept(params.get(i), i);
		}
	}
	
}
