package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

// Collect fields, for order by and group by
abstract class Collector_Fields<MODEL> extends BaseQueryEntity<MODEL> {

	private final List<Getter> getters;

	Collector_Fields(BaseQueryEntity<MODEL> last) {
		super(last);

		this.getters = new ArrayList<>();
	}

	final <T, R> void add(Getter getter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		getters.add(getter);
	}
	
	FunctionGetter [] toArray() {
		return getters.toArray(new FunctionGetter[getters.size()]);
	}
}
