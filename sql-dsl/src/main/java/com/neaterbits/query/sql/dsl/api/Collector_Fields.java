package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

// Collect fields, for order by and group by
abstract class Collector_Fields<MODEL> extends Collector_Base<MODEL> {

	private final List<Getter> getters;

	Collector_Fields(Collector_Base<MODEL> last) {
		super(last);

		this.getters = new ArrayList<>();
	}

	final <T, R> void add(Getter getter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		getters.add(getter);
	}
	
	Getter [] toArray() {
		return getters.toArray(new Getter[getters.size()]);
	}
}
